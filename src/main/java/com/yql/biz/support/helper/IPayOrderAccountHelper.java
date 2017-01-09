package com.yql.biz.support.helper;

import com.yql.biz.model.PayOrderAccount;
import com.yql.biz.vo.PayOrderVo;
import com.yql.core.web.ResponseModel;

/**
 * <p> 支付订单helper </p>
 * @auther simple
 * data 2016/12/26 0026.
 */
public interface IPayOrderAccountHelper {
    /**
     * 保存订单同时也会保存订单的历史
     * @param payOrderAccount 支付订单对象
     */
    void saveOrder(PayOrderAccount payOrderAccount);

    /**
     * 根据不同的渠道支付，保存订单已经订单历史
     * @param payOrderAccount 支付订单对象
     * @param userCode 用户code
     */
    void saveOrderTransform(PayOrderAccount payOrderAccount,String userCode);

    /**
     * 根据账户中心返回 执行成功或者失败操作
      * @param responseModel 账户中心返回对象
     * @param payOrderVo 支付订单VO
     * @param payOrderAccount   支付订单对象
     */
    void paymentResponse(ResponseModel responseModel, PayOrderVo payOrderVo,PayOrderAccount payOrderAccount);
}
