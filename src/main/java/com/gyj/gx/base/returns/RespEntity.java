package com.gyj.gx.base.returns;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RespEntity {

    private int code;

    private String message;

    private Object data;

    public RespEntity(){

    }

    public RespEntity(RespCode rc,Object data) {
        this.code = rc.getCode();
        this.message = rc.getMessage();
        this.data = data;
    }

    public RespEntity(RespCode rc){
        this.code = rc.getCode();
        this.message = rc.getMessage();
    }

    public RespEntity(int code,String msg){
        this.code = code;
        this.message = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
