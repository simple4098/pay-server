package com.yql.biz.vo.pay;

/**
 * <p>  </p>
 * @auther simple
 * data 2016/11/16 0016.
 */
public class Param {
    //请求报文 base64编码
    private String message;
    //请求报文 加密后的密文
    private String  signature;

    public Param() {
    }

    public Param(String message, String signature) {
        this.message = message;
        this.signature = signature;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
