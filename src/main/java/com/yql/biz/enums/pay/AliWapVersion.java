package com.yql.biz.enums.pay;

/**
 * <p> 描述 </p>
 *
 * @auther simple
 * data 2016/12/20 0020.
 */
public enum  AliWapVersion {

    ALI_VERSION("1.0");

    private String value;

    AliWapVersion(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
