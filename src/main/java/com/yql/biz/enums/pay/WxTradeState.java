package com.yql.biz.enums.pay;

/**
 * <p> 微信查询订单状态 </p>
 *
 *  SUCCESS—支付成功
 *  REFUND—转入退款
 *  NOTPAY—未支付
 *  CLOSED—已关闭
 *  REVOKED—已撤销（刷卡支付）
 *  USERPAYING--用户支付中
 *  PAYERROR--支付失败(其他原因，如银行返回失败)
 * @auther simple
 * data 2016/11/28 0025.
 */
public enum WxTradeState {
    SUCCESS("支付成功"),REFUND("REFUND"),NOTPAY("NOTPAY"),CLOSED("已关闭"),REVOKED("已撤销（刷卡支付）"),USERPAYING("用户支付中"),PAYERROR("支付失败");
    private String desc;

    WxTradeState(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
