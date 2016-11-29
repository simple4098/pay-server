package com.yql.biz.enums;

/**
 * <p>支付类型</p>
 * creator simple
 * data 2016/11/7 0007.
 */
public enum PayType {

    ACCOUNT("余额支付"),
    QUICK_PAYMENT("银行卡快捷支付"),
    DIAMOND("钻石支付"),
    WX_PAY("微信支付"),
    ALI_PAY("支付宝支付");
    private String value;

    PayType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
