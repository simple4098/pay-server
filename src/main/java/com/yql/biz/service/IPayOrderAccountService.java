package com.yql.biz.service;

import com.yql.biz.vo.DrawMoneyVo;
import com.yql.biz.vo.PayOrderVo;
import com.yql.biz.vo.ResultPayOrder;
import com.yql.biz.vo.ResultWxQueryOrder;
import com.yql.biz.vo.pay.response.WeiXinResponse;
import com.yql.biz.vo.pay.response.WeiXinResponseResult;
import com.yql.biz.vo.pay.wx.ResponseHandler;

import java.util.List;

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

    /**
     * 更新提现状态
     * @param payOrderNo 提现订单号
     */
    void updateDrawMoneyStatus(String payOrderNo,Integer payStatus);

    /**
     * 查询提现列表
     */
    List<DrawMoneyVo> findDrawMoneyList();

    /**
     * 查询微信订单信息
     * @param orderNo par-server 订单号
     * @return
     */
    ResultWxQueryOrder findWxOrderInfo(String orderNo);
}
