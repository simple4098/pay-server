package com.yql.biz.vo.pay;

import com.yql.biz.vo.pay.request.Request;

/**
 * <p> 描述 </p>
 *
 * @auther simple
 * data 2016/11/16 0016.
 */
public class Param {
    private Request message;
    private String signature;

    public Request getMessage() {
        return message;
    }

    public void setMessage(Request message) {
        this.message = message;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
