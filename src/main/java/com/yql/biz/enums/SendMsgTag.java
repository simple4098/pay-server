package com.yql.biz.enums;

/**
 * <p> 发送事件tag </p>
 * @auther simple
 * data 2016/11/23 0023.
 */
public enum SendMsgTag {
    PAY_SERVER_BALANCE_SUCCESS("余额支付成功"),
    PAY_SERVER_BALANCE_UNSUCCESS("余额支付失败"),
    PAY_SERVER_QUICK_PAYMENT_SUCCESS("快捷支付成功"),
    PAY_SERVER_QUICK_PAYMENT_UNSUCCESS("快捷支付失败"),
    PAY_SERVER_DIAMOND_SUCCESS("钻石支付成功"),
    PAY_SERVER_DIAMOND_UNSUCCESS("钻石支付失败");

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
