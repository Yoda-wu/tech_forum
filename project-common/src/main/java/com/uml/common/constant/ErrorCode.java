package com.uml.common.constant;

/**
 * @author wuyuda
 * @date 2022-03-22 15:39
 */
public enum ErrorCode {

    /**
     * 成功
     */
    SUCCESS(0,"请求成功"),

    /**
     * 失败
     */
    FAIL(444,"请求失败");
    private final int code;
    private final String message;
    public static final String ERROR_CODE_KEY = "errCode";
    public static final String ERROR_MESSAGE_KEY = "errMsg";
    ErrorCode(int value,String msg){
        code = value;
        message = msg;
    }
    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ErrorCode{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }

}
