package com.yql.biz.vo;

import com.yql.biz.enums.CardType;

/**
 * <p> 绑定银行卡 返回对象 </p>
 * @auther simple
 * data 2016/11/24 0024.
 */
public class ResultBangBank {
    //10=绑定处理中 20=绑定失败 30=绑定成功
    private Integer status;
    private CardType cardType;
    private String bankName;

    public ResultBangBank(Integer status) {
        this.status = status;
    }

    public ResultBangBank() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
