package com.yql.biz.vo.pay.ali;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * <p> 支付宝订单退款 </p>
 * @auther simple
 * data 2016/12/16 0016.
 */
public class AliRefundOrderRequest extends AliQueryOrderRequest {

    private String outRequestNo;

    public AliRefundOrderRequest(String outTradeNo, String tradeNo, String outRequestNo) {
        super(outTradeNo, tradeNo);
        this.outRequestNo = outRequestNo;
    }

    public AliRefundOrderRequest(String outRequestNo) {
        this.outRequestNo = outRequestNo;
    }

    @JSONField(name = "out_request_no")
    public String getOutRequestNo() {
        return outRequestNo;
    }

    public void setOutRequestNo(String outRequestNo) {
        this.outRequestNo = outRequestNo;
    }
}
