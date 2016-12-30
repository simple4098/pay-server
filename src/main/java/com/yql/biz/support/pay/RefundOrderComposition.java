package com.yql.biz.support.pay;

import com.yql.biz.model.PayOrderAccount;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author simple
 */
@Component
public class RefundOrderComposition {

    @Resource
    private IRefundOrder[] refundOrders;

    public boolean transform(PayOrderAccount payOrderAccount) {
        for (IRefundOrder refundOrder : refundOrders) {
            if (refundOrder.supports(payOrderAccount)) {
                return refundOrder.refundOrder(payOrderAccount);
            }
        }
        throw new UnsupportedOperationException("No suitable ICloseOrder!");
    }
}