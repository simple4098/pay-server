package com.yql.biz.enums;

/**
 * <p> 监听消息Tag类型 </p>
 * @auther simple
 * data 2016/11/23 0023.
 */
public enum ListenerTagType {

    USER_CENTER_USER_REGISTER("用户注册"),USER_CENTER_USER_REAL_NAME_AUTH("用户实名认证");

    ListenerTagType(String value) {
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
