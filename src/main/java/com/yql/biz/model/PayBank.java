package com.yql.biz.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>支付银行实体类</p>
 * @author  simple
 * @version 1.0.0
 * data 2016/11/7 0007.
 */
@Entity
@Table(name = "pay_bank")
public class PayBank extends Domain  {
    private Integer payAccountId;
    private String userCode;
    //持卡人
    private String cardholder;
    //银行卡
    private String bankCard;
    //银行卡名称
    private String bankName;
    //快捷支付金额
    private BigDecimal quickPaymentAmount;
    //排序字段
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sort;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Integer getPayAccountId() {
        return payAccountId;
    }

    public void setPayAccountId(Integer payAccountId) {
        this.payAccountId = payAccountId;
    }

    public String getCardholder() {
        return cardholder;
    }

    public void setCardholder(String cardholder) {
        this.cardholder = cardholder;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public BigDecimal getQuickPaymentAmount() {
        return quickPaymentAmount;
    }

    public void setQuickPaymentAmount(BigDecimal quickPaymentAmount) {
        this.quickPaymentAmount = quickPaymentAmount;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
