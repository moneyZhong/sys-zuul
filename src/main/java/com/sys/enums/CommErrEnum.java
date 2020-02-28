package com.sys.enums;

public enum CommErrEnum implements  BaseErrorInfoInterface{
    SUCCESS("200", "成功!"),
    BODY_NOT_MATCH("400","缺少必要参数"),
    SIGNATURE_NOT_MATCH("401","请求的数字签名不匹配!"),
    SIGNATURE_NOT_RIGHT("406","签名错误!"),
    REQUEST_TIME_OVER_FINFTEEN_MIN("402","请求时间与服务器时间不匹配!"),
    NOT_FOUND("404", "未找到该资源!"),
    INTERNAL_SERVER_ERROR("500", "服务器内部错误!"),
    SERVER_BUSY("503","服务器正忙，请稍后再试!")
            ;

    /** 错误码 */
    private String resultCode;

    /** 错误描述 */
    private String resultMsg;

    CommErrEnum(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    @Override
    public String getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }
}
