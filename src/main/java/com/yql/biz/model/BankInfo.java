package com.yql.biz.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <p> 支付平台提供银行信息 </p>
 * @auther simple
 * data 2016/11/18 0018.
 */
@Entity
@Table(name = "bank_info")
public class BankInfo extends Domain{
    private String bankCode;
    private String bankName;

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
