package com.yql.biz.service;

import com.yql.biz.vo.PayOrderVo;
import com.yql.biz.vo.ResultPayOrder;

/**
 * <p>支付账单</p>
 * creator simple
 * data 2016/11/10 0010.
 */
public interface IPayOrderAccountService {

    /**
     * 订单支付接口
     * @param payOrderVo 提交订单类型
     */
    ResultPayOrder order(PayOrderVo payOrderVo);

}
