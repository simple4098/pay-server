package com.yql.biz.enums.fy;

/**
 * <p> 富友请求类型 </p>
 * @auther simple
 * data 2016/12/7 0007.
 */
public enum  FyRequestType {
    payforreq("代付");
    private String value;

    FyRequestType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
