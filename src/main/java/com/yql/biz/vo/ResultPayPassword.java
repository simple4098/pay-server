package com.yql.biz.vo;

/**
 * <p> 是否设置支付密码 </p>
 * @auther simple
 * data 2016/11/23 0023.
 */
public class ResultPayPassword {
    //是否设置过支付密码 true 设置过  false 没有设置过
    private boolean setPayPassword;
    //用户code
    private String userCode;

    public ResultPayPassword(boolean setPayPassword, String userCode) {
        this.setPayPassword = setPayPassword;
        this.userCode = userCode;
    }

    public boolean isSetPayPassword() {
        return setPayPassword;
    }

    public void setSetPayPassword(boolean setPayPassword) {
        this.setPayPassword = setPayPassword;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
