package com.gyj.gx.base.returns;

public enum RespCode {

    /**
     * 错误码枚举
     */
    USER_NOT_FOUND(101, "没有登录或会话超时"),
    ACCESS_TOKEN_EXPIRED(102,"token已过期"),

    SUCCESS(200, "success"),
    PASSWORD_INCORRECT(210, "账号或密码错误"),
    PHONE_REGSITED(211, "该手机号码已注册过"),
    PHONE_NOT_EXSIST(212, "手机号不存在"),
    PHONE_NUMBER_INVALID(213, "手机号码格式不正确"),
    PASSWORD_TOO_SHORT(215,"密码长度太短,至少输入6位密码"),
    VCODE_INCORRECT(217, "验证码不正确，请重新输入！"),
    PHONE_SMS_LIMIT(218, "同一手机号验证码发送超过每日限制次数"),
    OLD_PASSWORD_INCORRECT(219, "旧密码不正确"),
    CAPTCHA_EMPTY(220,"请输入验证码"),

    PARAMETERS_ERROR(400, "参数非法或格式有误 "),
    UN_AUTHORIZED(401,"认证未通过"),
    ACCESS_DENIED(403,"没有权限"),
    NOT_FOUND(404,"数据不存在"),
    UN_SIGN(405,"签名验证失败"),
    UN_SIGN_TIMEOUT(406,"签名超时"),
    UN_BODY_DECRYPT(407,"密文不正确，无法解密"),
    UN_BODY_FORMAT(408,"解密正确，数据格式错误"),
    SERVER_ERROR(500, "服务器错误"),
    GATEWAY_TIMEOUT(504, "服务器超时"),
    CUSTOM_ERROR(506, "自定义错误"),
            ;

    private final int code;
    private String message;

    private RespCode(int code, String message)
    {
        this.code = code;
        this.message = message;
    }

    public int getCode()
    {
        return code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }
}
