package com.yql.biz.vo.pay.response;

import com.yql.biz.enums.pay.WxTradeState;
import com.yql.biz.vo.pay.wx.WeiXinNotifyVo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p> 微信支付【查询订单】微信返回的对象 </p>
 * @auther simple
 * data 2016/11/16 0016.
 */
@XmlRootElement(name = "xml")
public class WeiXinQueryOrderResponse extends WeiXinNotifyVo {

    /**
     *  SUCCESS—支付成功
     *  REFUND—转入退款
     *  NOTPAY—未支付
     *  CLOSED—已关闭
     *  REVOKED—已撤销（刷卡支付）
     *  USERPAYING--用户支付中
     *  PAYERROR--支付失败(其他原因，如银行返回失败)
     */
    private WxTradeState tradeState;

    @XmlElement(name = "trade_state")
    public WxTradeState getTradeState() {
        return tradeState;
    }

    public void setTradeState(WxTradeState tradeState) {
        this.tradeState = tradeState;
    }
}
