package com.yql.biz.support.pay;

import com.yql.biz.model.PayOrderAccount;
import com.yql.biz.vo.PayOrderVo;
import com.yql.biz.vo.ResultWxQueryOrder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author simple
 */
@Component
public class QueryOrderComposition {

    @Resource
    private IQueryOrder[] queryOrders;

    public ResultWxQueryOrder transform(PayOrderAccount payOrderAccount) {
        for (IQueryOrder queryOrder : queryOrders) {
            if (queryOrder.supports(payOrderAccount)) {
                return queryOrder.queryOrder(payOrderAccount);
            }
        }
        throw new UnsupportedOperationException("No suitable QueryOrder!");
    }
}