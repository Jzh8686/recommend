package com.gyj.gx.base.exception;


import com.gyj.gx.base.returns.RespCode;

public class BusinessException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private RespCode respCode;

    private String msg;
    public BusinessException(RespCode respCode){
        super("业务异常返回----code:"+respCode.getCode()+"----message:"+respCode.getMessage());
        this.msg = respCode.getMessage();
        this.respCode = respCode;
    }

    public BusinessException(String urlStr, RespCode respCode) {
        super("业务异常返回----url:"+urlStr+"----code:"+respCode.getCode()+"----message:"+respCode.getMessage());
        this.msg = respCode.getMessage();
        this.respCode = respCode;
    }

    public BusinessException(RespCode respCode, String msg){
        super("业务异常返回----code:"+respCode.getCode()+"----message:"+msg);
        this.msg = msg;
        this.respCode = respCode;
    }

    public RespCode getRespCode() {
        return respCode;
    }

    public String getMsg() {
        return msg;
    }
}
