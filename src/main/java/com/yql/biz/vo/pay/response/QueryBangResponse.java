package com.yql.biz.vo.pay.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p> 查询银行卡绑定关系 response </p>
 * @auther simple
 * data 2016/11/17 0017.
 */
@XmlRootElement(name = "Response")
public class QueryBangResponse extends Response{

   private QueryBangResponseBody bangResponseBody;

    @XmlElement(name = "Body")
    public QueryBangResponseBody getBangResponseBody() {
        return bangResponseBody;
    }

    public void setBangResponseBody(QueryBangResponseBody bangResponseBody) {
        this.bangResponseBody = bangResponseBody;
    }
}
