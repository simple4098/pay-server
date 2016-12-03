package com.yql.biz.vo.pay.wx;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p> 封装微信查询订单对象 </p>
 *  该接口提供所有微信支付订单的查询，商户可以通过该接口主动查询订单状态，完成下一步的业务逻辑。
 *  需要调用查询接口的情况：
 ◆ 当商户后台、网络、服务器等出现异常，商户系统最终未接收到支付通知；
 ◆ 调用支付接口后，返回系统错误或未知交易状态情况；
 ◆ 调用被扫支付API，返回USERPAYING的状态；
 ◆ 调用关单或撤销接口API之前，需确认支付状态；
 * @auther simple
 * data 2016/11/28 0025.
 */
@XmlRootElement(name = "xml")
public class WxOrderQueryVo extends WeiXinOrderVo {
    //微信订单号
    private String transactionId;

    @XmlElement(name = "transaction_id")
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
