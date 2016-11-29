package com.yql.biz.support.pay;

import com.yql.biz.enums.PayType;
import com.yql.biz.vo.PayOrderVo;

/**
 * <p> 支付宝支付 </p>
 *
 * @auther simple
 * data 2016/11/25 0025.
 */
public class PayOrderAliPayCreator implements IPayOrderCreator {
    @Override
    public PayOrderVo transform(PayOrderVo payOrderVo) {
        return null;
    }

    @Override
    public boolean supports(PayOrderVo payOrderVo) {
        return PayType.ALI_PAY.equals(payOrderVo.getPayType());
    }
}
