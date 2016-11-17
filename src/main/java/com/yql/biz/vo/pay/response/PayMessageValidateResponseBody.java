package com.yql.biz.vo.pay.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p> 快捷支付短信验证 response Body </p>
 * @auther simple
 * data 2016/11/17 0017.
 */
@XmlRootElement(name = "Body")
public class PayMessageValidateResponseBody  extends  BaseBody{
    //机构代码
    private String institutionID;
    //支付交易流水号
    private String paymentNo;
    @XmlElement(name = "InstitutionID")
    public String getInstitutionID() {
        return institutionID;
    }
    public void setInstitutionID(String institutionID) {
        this.institutionID = institutionID;
    }
    @XmlElement(name = "PaymentNo")
    public String getPaymentNo() {
        return paymentNo;
    }
    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }

}
