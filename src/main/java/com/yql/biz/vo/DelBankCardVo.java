package com.yql.biz.vo;

import javax.validation.constraints.NotNull;

/**
 * <p> 移除支付银行卡参数对象 </p>
 * @auther simple
 * data 2016/12/2 0002.
 */
public class DelBankCardVo {

    @NotNull(message = "{com.yql.validation.constraints.txCode.notnull}")
    private String txCode;
    @NotNull(message = "{com.yql.validation.constraints.payAccountId.notnull}")
    private Integer payAccountId;
    @NotNull(message = "{com.yql.validation.constraints.payPassword.notnull}")
    private String payPassword;

    public String getTxCode() {
        return txCode;
    }

    public void setTxCode(String txCode) {
        this.txCode = txCode;
    }

    public Integer getPayAccountId() {
        return payAccountId;
    }

    public void setPayAccountId(Integer payAccountId) {
        this.payAccountId = payAccountId;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }
}
