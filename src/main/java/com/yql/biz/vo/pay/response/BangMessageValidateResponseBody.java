package com.yql.biz.vo.pay.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p> 绑定银行卡 验证响应body </p>
 * @auther simple
 * data 2016/11/16 0016.
 */
@XmlRootElement(name = "Body")
public class BangMessageValidateResponseBody extends BaseBody {
    //绑定流水号
    private String txSNBinding;
    //验证码
    private String sMSValidationCode;
    /**
     * 发卡机构代码 <b>银联返回，不是 Request 中的 BankID</b>
     */
    private String issInsCode;
    //支付卡类型
    private String payCardType;
    @XmlElement(name = "TxSNBinding")
    public String getTxSNBinding() {
        return txSNBinding;
    }

    public void setTxSNBinding(String txSNBinding) {
        this.txSNBinding = txSNBinding;
    }
    @XmlElement(name = "SMSValidationCode")
    public String getsMSValidationCode() {
        return sMSValidationCode;
    }

    public void setsMSValidationCode(String sMSValidationCode) {
        this.sMSValidationCode = sMSValidationCode;
    }

    @XmlElement(name = "IssInsCode")
    public String getIssInsCode() {
        return issInsCode;
    }

    public void setIssInsCode(String issInsCode) {
        this.issInsCode = issInsCode;
    }
    @XmlElement(name = "PayCardType")
    public String getPayCardType() {
        return payCardType;
    }

    public void setPayCardType(String payCardType) {
        this.payCardType = payCardType;
    }
}
