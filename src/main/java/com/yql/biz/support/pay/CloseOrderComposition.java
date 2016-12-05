package com.yql.biz.support.pay;

import com.yql.biz.model.PayOrderAccount;
import com.yql.biz.vo.ResultWxQueryOrder;
import com.yql.biz.vo.pay.response.WeiXinCloseOrderResponse;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author simple
 */
@Component
public class CloseOrderComposition {

    @Resource
    private ICloseOrder[] closeOrders;

    public WeiXinCloseOrderResponse transform(PayOrderAccount payOrderAccount) {
        for (ICloseOrder closeOrder : closeOrders) {
            if (closeOrder.supports(payOrderAccount)) {
                return closeOrder.closeOrder(payOrderAccount);
            }
        }
        throw new UnsupportedOperationException("No suitable ICloseOrder!");
    }
}