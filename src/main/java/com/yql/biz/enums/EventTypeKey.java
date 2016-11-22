package com.yql.biz.enums;

/**
 * <p>监听事件类型 </p>
 * @auther simple
 * data 2016/11/22 0022.
 */
public enum EventTypeKey {
    USER_REGISTER("用户注册"),USER_REAL_NAME_AUTH("用户实名认证");

    EventTypeKey(String value) {
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
