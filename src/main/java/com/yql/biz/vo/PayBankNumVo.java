package com.yql.biz.vo;

/**
 * <p>支付银行数量vo</p>
 * creator simple
 * data 2016/11/10 0010.
 */
public class PayBankNumVo {

    private String userCode;
    private long num;

    public PayBankNumVo(String userCode, long num) {
        this.userCode = userCode;
        this.num = num;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }
}
