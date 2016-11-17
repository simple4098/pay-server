package com.yql.biz.vo.pay.request;

import javax.xml.bind.annotation.*;

/**
 * <p> 大金支付平台请求类 </p>
 * @auther simple
 * data 2016/11/16 0016.
 */
@XmlRootElement(name = "Request")
    @XmlSeeAlso({BangMessageValidateBody.class, BangBody.class,PayMessageValidateBody.class,QueryBangBody.class,UninstallBangBody.class,PayBody.class})
@XmlType(propOrder = {"head","body"})
public class Request<T extends DjPay> extends DjPay{
    private Head head;
    private T body;
    @XmlElement(name = "Head")
    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }
    /*@XmlElement(name = "Body")*/
    @XmlAnyElement(lax = true)
    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
