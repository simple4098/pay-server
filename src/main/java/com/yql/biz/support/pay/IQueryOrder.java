package com.yql.biz.support.pay;

import com.yql.biz.model.PayOrderAccount;
import com.yql.biz.vo.ResultWxQueryOrder;

/**
 * <p>查询支付订单信息</p>
 * creator simple
 * data 2016/11/11 0011.
 */
public interface IQueryOrder {

    ResultWxQueryOrder queryOrder(PayOrderAccount payOrderAccount);

    boolean supports(PayOrderAccount payOrderAccount);
}
