package com.yql.biz.enums.fy;

/**
 * <p> 富友版本号 </p>
 * @auther simple
 * data 2016/12/6 0006.
 */
public enum FyVersion {
    CHECK_BANK_VERSION_130("1.30"), PAY_FOR_VERSION_100("1.00"), B2C_PAY_VERSION("1.0.1"), H5_PAY("2.0");
    private String value;

    FyVersion(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
