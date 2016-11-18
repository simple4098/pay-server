package com.yql.biz.model;

import com.yql.biz.enums.CardType;

import javax.persistence.*;
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
    private int sort;
    //银行卡id
    private String bankId;
    //交易编码
    private String txCode;
    //绑定流水
    @Column(name = "tx_SN_binding")
    private String txSNBinding;
    //手机号码
    private String phoneNumber;
    @Enumerated(value = EnumType.STRING)
    private CardType cardType;
    //信用卡有效期，格式 YYMM
    private Integer validDate;
    //信用卡背面的末 3 位数字
    private Integer cvn2;

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getTxCode() {
        return txCode;
    }

    public void setTxCode(String txCode) {
        this.txCode = txCode;
    }

    public String getTxSNBinding() {
        return txSNBinding;
    }

    public void setTxSNBinding(String txSNBinding) {
        this.txSNBinding = txSNBinding;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public Integer getValidDate() {
        return validDate;
    }

    public void setValidDate(Integer validDate) {
        this.validDate = validDate;
    }

    public Integer getCvn2() {
        return cvn2;
    }

    public void setCvn2(Integer cvn2) {
        this.cvn2 = cvn2;
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
