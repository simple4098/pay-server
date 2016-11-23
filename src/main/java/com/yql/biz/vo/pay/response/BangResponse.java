package com.yql.biz.vo.pay.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p> 绑定银行卡验证短信请求response</p>
 * @auther simple
 * data 2016/11/16 0016.
 */
@XmlRootElement(name = "Response")
public class BangResponse extends Response {

    private BangResponseBody bangResponseBody;

    @XmlElement(name = "Body")
    public BangResponseBody getBangResponseBody() {
        return bangResponseBody;
    }

    public void setBangResponseBody(BangResponseBody bangResponseBody) {
        this.bangResponseBody = bangResponseBody;
    }
}
