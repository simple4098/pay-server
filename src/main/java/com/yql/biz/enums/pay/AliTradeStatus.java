package com.yql.biz.enums.pay;

/**
 * <p> 阿里订单状态 </p>
 * @auther simple
 * data 2016/12/23 0023.
 */
public enum AliTradeStatus {
    WAIT_BUYER_PAY("交易创建，等待买家付款"),
    TRADE_CLOSED("未付款交易超时关闭，或支付完成后全额退款"),
    TRADE_SUCCESS("交易支付成功"),
    TRADE_FINISHED("交易结束，不可退款");
    private String desc;

    AliTradeStatus(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
