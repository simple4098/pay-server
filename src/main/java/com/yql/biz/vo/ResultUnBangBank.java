package com.yql.biz.vo;

import java.util.Date;

/**
 * <p> 解绑银行卡 返回对象 </p>
 * @auther simple
 * data 2016/11/24 0024.
 */
public class ResultUnBangBank {
    //10=解绑处理中 20=解绑成功 30=解绑失败（等于已绑定）
    private Integer status;
    //银行处理时间
    private Date bankTxTime;
    //响应代码
    private String responseCode;
    private String responseMessage;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getBankTxTime() {
        return bankTxTime;
    }

    public void setBankTxTime(Date bankTxTime) {
        this.bankTxTime = bankTxTime;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
