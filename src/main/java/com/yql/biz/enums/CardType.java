package com.yql.biz.enums;

/**
 * <p> 卡类型：10=个人借记 20=个人贷记 </p>
 * @auther simple
 * data 2016/11/18 0018.
 */
public enum CardType {
    //借记卡
    BANK_CARD(10),
    //借贷卡
    CREDIT(20);
    private int value;

    CardType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
