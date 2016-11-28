package com.yql.biz.enums.pay;

/**
 * <p> 微信支付类型 </p>
 * @auther simple
 * data 2016/11/26 0026.
 */
public enum WxPayType {
    APP("微信APP支付");
    private String value;

    WxPayType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
