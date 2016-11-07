package com.yql.biz.enums;

/**
 * <p>支付类型</p>
 * creator simple
 * data 2016/11/7 0007.
 */
public enum PayType {
    ACCOUNT("余额支付"),
    CARD("银行卡");

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
