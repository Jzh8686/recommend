package com.gyj.gx.controller;

import com.gyj.gx.base.constants.Constants;
import com.gyj.gx.base.exception.BusinessException;
import com.gyj.gx.base.filter.JsonLogFilter;
import com.gyj.gx.base.returns.RespCode;
import com.gyj.gx.base.returns.RespEntity;
import com.gyj.gx.base.util.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.ResourceAccessException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RespEntity handleExceptions(HttpServletRequest request, Exception ex) {
        String simpleName = ex.getClass().getSimpleName();
        String clientAbortException = "ClientAbortException";
        if (clientAbortException.equals(simpleName)) {
            //客户端取消链接不记录日志
            logger.debug(getRequestString(request)+ex.getMessage());
            return null;
        } else {
            logger.error(getRequestString(request),ex);
        }
        return new RespEntity(RespCode.SERVER_ERROR);
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RespEntity handleBusinessExceptions(HttpServletRequest request, BusinessException ex) {
        logger.info(getRequestString(request)+ex.getMessage());
        if(ex.getMessage()!=null){
            return new RespEntity(ex.getRespCode().getCode(),ex.getMsg());
        }
        return new RespEntity(ex.getRespCode());
    }



    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RespEntity handleMissingServletRequestParameterException(HttpServletRequest request, MissingServletRequestParameterException ex){
        logger.error(getRequestString(request)+ex.getMessage());
        return new RespEntity(RespCode.PARAMETERS_ERROR.getCode(),"参数 '"+ex.getParameterName()+"'不能为空");
    }


    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RespEntity handleHttpMediaTypeNotSupportedException(HttpServletRequest request, HttpMediaTypeNotSupportedException ex){
        logger.error(getRequestString(request)+ex.getMessage());
        return new RespEntity(RespCode.PARAMETERS_ERROR.getCode(),"不支持的contentType '"+ex.getContentType()+"'");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RespEntity handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException ex){
        logger.error(getRequestString(request),ex);
        List<ObjectError> errors= ex.getBindingResult().getAllErrors();
        System.out.println(ex.getMessage());
        return new RespEntity(RespCode.PARAMETERS_ERROR.getCode(),errors.get(0).getDefaultMessage());
    }

    @ExceptionHandler(ResourceAccessException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RespEntity handleResourceAccessException(HttpServletRequest request, ResourceAccessException ex){
        logger.error(getRequestString(request),ex);
        return new RespEntity(RespCode.SERVER_ERROR.getCode(),"服务器内部超时");
    }

    @ExceptionHandler(TypeMismatchException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RespEntity handleTypeMismatchException(HttpServletRequest request, TypeMismatchException ex){
        logger.error(getRequestString(request),ex);
        return new RespEntity(RespCode.PARAMETERS_ERROR.getCode(),"参数'"+ex.getValue()+"'类型错误");
    }

    private static String getRequestString(HttpServletRequest request){
        return getRequestParameter(request)+" 请求错误";
    }


    public static String getRequestParameter(HttpServletRequest request){
        StringBuffer sb = new StringBuffer();
        sb.append(IpUtil.getRemoteAddress(request)).append(" ")
                .append(request.getMethod()).append(" ")
                .append(request.getRequestURI()).append(" ");
        if(request.getContentType() !=null){
            sb.append(request.getContentType()).append(" ");
        }
        sb.append(getCustomerHeaderString(request)).append(" ");
        if(request.getParameterMap()!=null&&request.getParameterMap().size()!=0){
            sb.append(parameterMapToString(request.getParameterMap())).append(" ");
        }
        if(isJson(request)){
            sb.append(readJson(request)).append(" ");
        }
        if(isMultipart(request)){
            sb.append(humanReadableByteCount(request.getContentLength())).append(" ");
        }
        return sb.toString();
    }

    private static boolean isMultipart(final HttpServletRequest request) {
        return request.getContentType()!=null && request.getContentType().startsWith("multipart/form-data");
    }

    private static boolean isJson(final HttpServletRequest request) {
        return request.getContentType()!=null && request.getContentType().startsWith("application/json");
    }

    private static String humanReadableByteCount(long bytes) {
        int unit = 1024;
        if (bytes < unit){
            return bytes + " B";
        }
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = String.valueOf("KMGTPE".charAt(exp-1));
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }

    private static String parameterMapToString(Map<String, String[]> map){
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String[]> entry : map.entrySet())
        {
            sb.append(entry.getKey() + "=" + entry.getValue()[0]+"&");
        }
        return sb.substring(0, sb.length()-1);
    }

    private static String getCustomerHeaderString(HttpServletRequest request){
        String token= "";
        if(request.getCookies()!=null){
            for (Cookie cookie : request.getCookies()) {
                if(cookie.getName().equals(Constants.TOKEN_COOKIE_NAME)){
                    token = " token:"+cookie.getValue();
                }
            }
        }

        return "("+token+")";
    }

    private static String readJson(HttpServletRequest request){
        return (String)request.getAttribute(JsonLogFilter.JSON_STRING);
    }
}
