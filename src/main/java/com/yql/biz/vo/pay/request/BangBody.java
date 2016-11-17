package com.yql.biz.vo.pay.request;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p> 大金支付平台绑定银行卡body类 </p>
 * @auther simple
 * data 2016/11/16 0016.
 */
@XmlRootElement(name = "Body")
public class BangBody extends DjPay {
    //绑定流水号
    private String txSNBinding;
    //银行 ID，参考《银行编码表》
    private String bankId;
    //账户名称
    private String AccountName;
    //账户号码
    private String accountNumber;
    //证件类型
    private Integer identificationType;
    //证件号码
    private String identificationNumber;
    //手机号码
    private String phoneNumber;
    //卡片类型
    private Integer cardType;
    //绑定信用卡该项必填 有效期 YYMM
    private Integer validDate;
    //信用卡背面的末 3 位数字 绑定信用卡该项必填
    private Integer cVN2;

    @XmlElement(name = "TxSNBinding")
    public String getTxSNBinding() {
        return txSNBinding;
    }

    public void setTxSNBinding(String txSNBinding) {
        this.txSNBinding = txSNBinding;
    }
    @XmlElement(name = "BankId")
    public String getBankId() {
        return bankId;
    }
    public void setBankId(String bankId) {
        this.bankId = bankId;
    }
    @XmlElement(name = "AccountName")
    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String accountName) {
        this.AccountName = accountName;
    }
    @XmlElement(name = "AccountNumber")
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    @XmlElement(name = "IdentificationType")
    public Integer getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(Integer identificationType) {
        this.identificationType = identificationType;
    }
    @XmlElement(name = "IdentificationNumber")
    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }
    @XmlElement(name = "PhoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @XmlElement(name = "CardType")
    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }
    @XmlElement(name = "ValidDate")
    public Integer getValidDate() {
        return validDate;
    }

    public void setValidDate(Integer validDate) {
        this.validDate = validDate;
    }
    @XmlElement(name = "CVN2")
    public Integer getcVN2() {
        return cVN2;
    }

    public void setcVN2(Integer cVN2) {
        this.cVN2 = cVN2;
    }
}
