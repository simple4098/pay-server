package com.yql.biz.vo.pay.response;

import com.yql.biz.enums.pay.WxPayResult;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p> 异步通知返回给微信对象 </p>
 * @auther simple
 * data 2016/11/16 0016.
 */
@XmlRootElement(name = "xml")
public class WeiXinResponseResult {
    private WxPayResult returnCode;
    private String returnMsg;

    public WeiXinResponseResult() {
    }

    public WeiXinResponseResult(WxPayResult returnCode) {
        this.returnCode = returnCode;
    }

    @XmlElement(name = "return_code")
    public WxPayResult getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(WxPayResult returnCode) {
        this.returnCode = returnCode;
    }

    @XmlElement(name = "return_msg")
    public String getReturnMsg() {
        return returnMsg;
    }
    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }
}
