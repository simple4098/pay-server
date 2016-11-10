package com.yql.biz.vo;

import com.alibaba.fastjson.JSON;
import com.yql.biz.model.PayAccount;
import com.yql.biz.model.PayBank;

import java.math.BigDecimal;

/**
 * <p>支付银行vo</p>
 * creator simple
 * data 2016/11/10 0010.
 */
public class PayBankVo {
    private String userCode;
    private Integer payAccountId;
    //银行卡
    private String bankCard;
    //银行卡名称
    private String bankName;
    //持卡人
    private String cardholder;
    //快捷支付金额
    private BigDecimal quickPaymentAmount;
    //排序字段
    private int sort;

    public String getCardholder() {
        return cardholder;
    }

    public void setCardholder(String cardholder) {
        this.cardholder = cardholder;
    }

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

    public static PayBank voToDomain(PayBankVo payBankVo,PayAccount payAccount){
        String json = JSON.toJSONString(payBankVo);
        PayBank payBank = JSON.parseObject(json, PayBank.class);
        payBank.setPayAccountId(payAccount.getId());
        return payBank;
    }
}
