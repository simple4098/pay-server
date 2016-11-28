package com.yql.biz.enums;

/**
 * <p> 发送事件tag </p>
 * @auther simple
 * data 2016/11/23 0023.
 */
public enum SendMsgTag {
    PAY_SERVER_STATUS("支付消息"),
    PAY_SERVER_WX_CALLBACK("微信支付异步通知"),
    PAY_SERVER_ALIPAY_CALLBACK("支付宝支付异步通知");


    SendMsgTag(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
