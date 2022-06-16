package com.lyx.autoperm.exception;

/**
 * @author 黎勇炫
 * @date 2022年06月10日 9:52
 */
public enum UserCodeEnum {
    /**

     */
    USER_NOT_FOUNT(10001,"用户不存在"),
    USERNAME_IS_EMPTY(10002,"用户名为空"),
    AUTHENTICATION_FAILED(10003,"系统异常，账号认证失败"),
    TOKEN_NOT_FOUND(10004,"暂无登录信息");


    private Integer code;
    private String message;

    UserCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
