package com.yql.biz.controller;

import com.alibaba.fastjson.JSON;
import com.yql.biz.constraint.OrderNo;
import com.yql.biz.service.IPayOrderAccountService;
import com.yql.biz.util.PlatformPayUtil;
import com.yql.biz.vo.*;
import com.yql.biz.vo.pay.response.Response;
import com.yql.biz.vo.pay.response.WeiXinCloseOrderResponse;
import com.yql.biz.vo.pay.wx.WeiXinAppRequest;
import com.yql.biz.web.ResponseModel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * <p>支付订单</p>
 * @author  simple on 2016/11/3 0003.
 */
@RestController
@RequestMapping("/account")
@Validated
public class PayOrderController {

    @Resource
    private IPayOrderAccountService payOrderAccountService;



    /**
     * <p>账单支付</p>
     * <p>余额提现</p>
     * @param payOrderVo 支付账单信息
     */
    @RequestMapping("/order")
    public ResponseModel index(@Validated PayOrderVo payOrderVo){
        ResultPayOrder order = payOrderAccountService.order(payOrderVo);
        return ResponseModel.SUCCESS(order);
    }

    /**
     * 提现状态修改
     * @param orderNo 订单号
     * @param payStatus 订单状态
     * @see ( com.yql.biz.enums.pay.PayStatus  )  订单状态参考
     *
     * @return
     */
    @RequestMapping("/draw_money_status")
    public ResponseModel drawMoneyStatus(@OrderNo String orderNo, @NotNull(message = "com.yql.validation.constraints.payStatus.message") Integer payStatus){
        payOrderAccountService.updateDrawMoneyStatus(orderNo,payStatus);
        return ResponseModel.SUCCESS();
    }

    /**
     * 提现列表
     * @return
     */
    @RequestMapping("/draw_money_list")
    public ResponseModel drawMoneyList(){
        List<DrawMoneyVo>  list = payOrderAccountService.findDrawMoneyList();
        return ResponseModel.SUCCESS(list);
    }

    /**
     * 查询订单的支付平台信息
     * @param orderNo 订单号
     */
    @RequestMapping("/query_order")
    public ResponseModel queryOrder(@OrderNo String orderNo){
        ResultWxQueryOrder resultWxQueryOrder =   payOrderAccountService.findWxOrderInfo(orderNo);
        return ResponseModel.SUCCESS(resultWxQueryOrder);
    }

    /**
     * 关闭订单
     * @param orderNo 订单号
     */
    @RequestMapping("/close_order")
    public ResponseModel closeOrder(@OrderNo String orderNo){
        WeiXinCloseOrderResponse weiXinCloseOrderResponse =   payOrderAccountService.closeOrder(orderNo);
        return ResponseModel.SUCCESS(weiXinCloseOrderResponse);
    }

    /**
     * 获取移动端信息
     * @param orderNo 订单号
     * @param spbillCreateIp 用户ip
     */
    @RequestMapping("/wx_prepay")
    public ResponseModel prepay(@OrderNo String orderNo,@NotNull(message = "{com.yql.validation.constraints.txCode.spbillCreateIp}") String spbillCreateIp){
        AppPrepayInfo appPrepayInfo =   payOrderAccountService.prepay(orderNo,spbillCreateIp);
        return ResponseModel.SUCCESS(appPrepayInfo);
    }

    /**
     * 支付平台通知商户支付成功 失败
     */
    // TODO: 2016/11/18 0018
    @RequestMapping("/pay/notice")
    public Response payNotice(){

        return  null;
    }

}
