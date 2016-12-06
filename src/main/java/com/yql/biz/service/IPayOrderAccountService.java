package com.yql.biz.service;

import com.yql.biz.vo.*;
import com.yql.biz.vo.pay.response.WeiXinCloseOrderResponse;
import com.yql.biz.vo.pay.response.WeiXinResponse;
import com.yql.biz.vo.pay.response.WeiXinResponseResult;
import com.yql.biz.vo.pay.wx.ResponseHandler;
import com.yql.biz.vo.pay.wx.WeiXinAppRequest;

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

    /**
     * 关闭订单
     * @param orderNo
     * @return
     */
    WeiXinCloseOrderResponse closeOrder(String orderNo);

    /**
     * 移动端获取预付单信息
     * @param orderNo 订单号
     * @param spbillCreateIp 用户ip
     * @return
     */
    AppPrepayInfo prepay(String orderNo, String spbillCreateIp);

    /**
     * 查询订单信息
     * @param orderNo 订单号
     * @return
     */
    ResultPayOrder findOrderInfo(String orderNo);
}
