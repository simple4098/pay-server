package com.yql.biz.vo.pay.request;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p> 快捷支付短信验证请求body（短信） </p>
 * @auther simple
 * data 2016/11/17 0017.
 */
@XmlRootElement(name = "Body")
public class PayMessageValidateBody extends DjPay {
    //支付交易流水号
    private String paymentNo;
    //短信验证码
    private String SMSValidationCode;
    @XmlElement(name = "PaymentNo")
    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }
    @XmlElement(name = "SMSValidationCode")
    public String getSMSValidationCode() {
        return SMSValidationCode;
    }

    public void setSMSValidationCode(String SMSValidationCode) {
        this.SMSValidationCode = SMSValidationCode;
    }
}
