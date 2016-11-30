package com.yql.biz.service;

import com.yql.biz.vo.PayOrderVo;
import com.yql.biz.vo.ResultPayOrder;
import com.yql.biz.vo.pay.response.WeiXinResponse;
import com.yql.biz.vo.pay.response.WeiXinResponseResult;
import com.yql.biz.vo.pay.wx.ResponseHandler;

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

    /**
     * 微信回调
     * @param responseHandler
     */
    WeiXinResponseResult callPayNotify(ResponseHandler responseHandler);
}
