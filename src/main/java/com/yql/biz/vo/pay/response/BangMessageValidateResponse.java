package com.yql.biz.vo.pay.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p> 绑定银行卡验证短信请求response</p>
 * @auther simple
 * data 2016/11/16 0016.
 */
@XmlRootElement(name = "Response")
public class BangMessageValidateResponse extends Response {

    private BangMessageValidateResponseBody bangMessageValidateResponseBody;

    @XmlElement(name = "Body")
    public BangMessageValidateResponseBody getBangMessageValidateResponseBody() {
        return bangMessageValidateResponseBody;
    }

    public void setBangMessageValidateResponseBody(BangMessageValidateResponseBody bangMessageValidateResponseBody) {
        this.bangMessageValidateResponseBody = bangMessageValidateResponseBody;
    }
}
