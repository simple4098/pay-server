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

    public static PayMessageValidateResponse toBean(){
        PayMessageValidateResponse response = new PayMessageValidateResponse();
        ResponseHead responseHead = new ResponseHead();
        responseHead.setMessage("支付连接超时");
        responseHead.setCode("2000");
        response.setHead(responseHead);
        return  response;
    }
}
