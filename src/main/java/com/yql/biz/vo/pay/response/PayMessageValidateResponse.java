package com.yql.biz.vo.pay.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p> 快捷支付短信验证 response  </p>
 * @auther simple
 * data 2016/11/17 0017.
 */
@XmlRootElement(name = "Response")
public class PayMessageValidateResponse extends Response{
    private PayMessageValidateResponseBody payMessageValidateResponseBody;

    @XmlElement(name = "Body")
    public PayMessageValidateResponseBody getPayMessageValidateResponseBody() {
        return payMessageValidateResponseBody;
    }

    public void setPayMessageValidateResponseBody(PayMessageValidateResponseBody payMessageValidateResponseBody) {
        this.payMessageValidateResponseBody = payMessageValidateResponseBody;
    }
}
