package com.yql.biz.vo;

import com.yql.biz.constraint.Password;

import javax.validation.constraints.NotNull;

/**
 * <p>找回支付密码vo对象</p>
 * creator simple
 * data 2016/11/10 0010.
 */
public class ResetPayPasswordVo {
    @NotNull(message = "{com.yql.validation.constraints.userCode.message}")
    private String userCode;
    //支付密码
    @Password
    private String payPassword;
    //电话号码
    private String phone;
    //手机验证码
    private String phoneCode;
    //redis对应的业务key
    private String phoneCodeKey;

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getPhoneCodeKey() {
        return phoneCodeKey;
    }

    public void setPhoneCodeKey(String phoneCodeKey) {
        this.phoneCodeKey = phoneCodeKey;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
