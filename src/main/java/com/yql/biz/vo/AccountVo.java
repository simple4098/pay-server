package com.yql.biz.vo;

import java.math.BigDecimal;

/**
 * <p> 账户中心 返回数据模型 </p>
 * @auther simple
 * data 2016/11/21 0021.
 */
public class AccountVo {
    //现金余额
    private BigDecimal cashFee;
    //金币余额
    private BigDecimal goldFee;
    //用户code
    private String userCode;
    //钻石余额
    private BigDecimal diamondFee;

    public BigDecimal getCashFee() {
        return cashFee;
    }

    public void setCashFee(BigDecimal cashFee) {
        this.cashFee = cashFee;
    }

    public BigDecimal getGoldFee() {
        return goldFee;
    }

    public void setGoldFee(BigDecimal goldFee) {
        this.goldFee = goldFee;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public BigDecimal getDiamondFee() {
        return diamondFee;
    }

    public void setDiamondFee(BigDecimal diamondFee) {
        this.diamondFee = diamondFee;
    }
}
