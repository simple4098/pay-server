package com.yql.biz.vo.pay.response;

import com.yql.biz.vo.pay.request.DjPay;

import javax.xml.bind.annotation.XmlElement;
import java.util.Date;

/**
 * <p> response 公共的body部分属性 </p>
 * @auther simple
 * data 2016/11/17 0017.
 */
public class BaseBody extends DjPay {
    //短信验证状态 20=验证码超时 30=验证未通过 40=验证通过
    private Integer verifyStatus;
    /**交易状态
     * 10=绑定处理中
     * 20=绑定失败
     * 30=绑定成功
     */
    private Integer status;
    //银行处理时间
    private Date bankTxTime;
    //响应代码
    private String responseCode;
    private String responseMessage;

    @XmlElement(name = "VerifyStatus")
    public Integer getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(Integer verifyStatus) {
        this.verifyStatus = verifyStatus;
    }
    @XmlElement(name = "Status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    @XmlElement(name = "BankTxTime")
    public Date getBankTxTime() {
        return bankTxTime;
    }

    public void setBankTxTime(Date bankTxTime) {
        this.bankTxTime = bankTxTime;
    }
    @XmlElement(name = "ResponseCode")
    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }
    @XmlElement(name = "ResponseMessage")
    public String getResponseMessage() {
        return responseMessage;
    }
    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
