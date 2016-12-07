package com.yql.biz.enums.fy;

/**
 * <p> 富友版本号 </p>
 * @auther simple
 * data 2016/12/6 0006.
 */
public enum FyVersion {
    VERSION_130("1.30"),VERSION_100("1.00");
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
