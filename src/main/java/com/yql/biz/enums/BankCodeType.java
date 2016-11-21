package com.yql.biz.enums;

/**
 * <p> 生产银行卡编码类型 </p>
 * @auther simple
 * data 2016/11/21 0021.
 */
public enum BankCodeType {

    SN_BINDING("绑定编码"),TX_SN_UN_BINDING("解绑编码"),TX_CODE("交易编码"),SETTLEMENTFLAG("结算标识");

    BankCodeType(String value) {
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
