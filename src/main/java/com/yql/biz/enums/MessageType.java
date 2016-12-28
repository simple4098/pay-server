package com.yql.biz.enums;

/**
 * <p> 描述 </p>
 *
 * @auther simple
 * data 2016/12/28 0028.
 */
public enum MessageType {
    AUTHENTICATION_CODE("身份验证码"),
    LOGIN_CONFIRM("登录确认码"),LOGIN_EXCEPTION("登录异常验证码"),USER_REGISTER("用户注册验证码"),
    ACTIVITY_CONFIRM("活动确认验证码"),UPDATE_PASSWORD("修改密码验证码"),INFORMATION_UPDATE("信息变更验证码");
    private String desc;

    MessageType(String desc) {
        this.desc = desc;
    }
}
