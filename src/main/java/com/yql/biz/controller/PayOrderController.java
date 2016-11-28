package com.yql.biz.controller;

import com.yql.biz.service.IPayOrderAccountService;
import com.yql.biz.vo.PayOrderVo;
import com.yql.biz.vo.ResultPayOrder;
import com.yql.biz.vo.pay.response.Response;
import com.yql.biz.web.ResponseModel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
     * 账单支付
     * @param payOrderVo 支付账单信息
     */
    @RequestMapping("/order")
    public ResponseModel index(@Validated PayOrderVo payOrderVo){
        ResultPayOrder order = payOrderAccountService.order(payOrderVo);
        return ResponseModel.SUCCESS(order);
    }

    /**
     * 用户提现
     * @param payOrderVo
     * @return
     */
     @RequestMapping("/draw_money")
    public ResponseModel drawMoney(@Validated PayOrderVo payOrderVo){



        return ResponseModel.SUCCESS();
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
