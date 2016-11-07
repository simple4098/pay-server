package com.yql.biz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
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
public class PayBank extends Domain implements Serializable {
    @Column(name = "pay_account_id")
    private Long payAccountId;
    //银行卡
    @Column(name = "bankCard")
    private String bankCard;
    //银行卡名称
    @Column(name = "bank_name")
    private String bankName;
    //快捷支付金额
    @Column(name = "quick_payment_amount")
    private BigDecimal quickPaymentAmount;
    //排序字段
    @Column(name = "sort")
    private int sort;

    public Long getPayAccountId() {
        return payAccountId;
    }

    public void setPayAccountId(Long payAccountId) {
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
}
