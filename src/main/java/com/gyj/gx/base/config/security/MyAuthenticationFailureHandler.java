package com.gyj.gx.base.config.security;

import com.alibaba.fastjson.JSON;
import com.gyj.gx.base.returns.RespCode;
import com.gyj.gx.base.returns.RespEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        AuthenticationException e) throws IOException, ServletException {

        RespEntity respEntity;
        if (e instanceof BadCredentialsException) {
            respEntity = new RespEntity(RespCode.PASSWORD_INCORRECT);
        }else if (e instanceof LockedException){
            respEntity = new RespEntity(RespCode.CUSTOM_ERROR.getCode(),"账号被冻结");
        }else {
            respEntity = new RespEntity(RespCode.PASSWORD_INCORRECT);
        }

//        httpServletResponse.setCharacterEncoding("UTF-8");

        httpServletResponse.setHeader("Content-Type", "text/html; charset=UTF-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(respEntity));
    }
}
