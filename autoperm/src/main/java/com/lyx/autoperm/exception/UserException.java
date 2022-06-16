package com.lyx.autoperm.exception;

/**
 * @author 黎勇炫
 * @date 2022年06月10日 9:51
 */
public class UserException extends RuntimeException{
    /**
     * 错误码
     */
    private Integer code;

    /**
     * 信息
     */
    private String message;

    public UserException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public UserException(UserCodeEnum userCodeEnum){
        super(userCodeEnum.getMessage());
        this.code = userCodeEnum.getCode();
        this.message = userCodeEnum.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
