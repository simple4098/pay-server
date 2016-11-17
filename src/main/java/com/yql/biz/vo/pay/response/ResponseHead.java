package com.yql.biz.vo.pay.response;

import javax.xml.bind.annotation.XmlElement;

/**
 * <p> 描述 </p>
 * @auther simple
 * data 2016/11/16 0016.
 */
public class ResponseHead {

    private String code;

    private String message;

    @XmlElement(name = "Code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @XmlElement(name = "Message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
