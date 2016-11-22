package com.yql.biz.vo.pay.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p> 描述 </p>
 * @auther simple
 * data 2016/11/16 0016.
 */

public class Response {
    private ResponseHead head;

    @XmlElement(name = "Head")
    public ResponseHead getHead() {
        return head;
    }

    public void setHead(ResponseHead head) {
        this.head = head;
    }
    public static Response toBean(){
        Response response = new Response();
        ResponseHead responseHead = new ResponseHead();
        responseHead.setMessage("支付连接超时");
        responseHead.setCode("2000");
        response.setHead(responseHead);
        return  response;
    }
}
