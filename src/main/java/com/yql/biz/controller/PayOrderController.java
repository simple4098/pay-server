package com.yql.biz.controller;

import com.yql.biz.service.IPayOrderAccountService;
import com.yql.biz.vo.PayOrderVo;
import com.yql.biz.web.ResponseModel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>支付订单</p>
 * @author  simple on 2016/11/3 0003.
 */
@RestController
@RequestMapping("/account")
/*@Validated*/
public class PayOrderController {

    @Resource
    private IPayOrderAccountService payOrderAccountService;


    /**
     * 账单支付
     * @param payOrderVo 支付账单信息
     */
    @RequestMapping("/order")
    @ResponseBody
    public ResponseModel index(@Validated PayOrderVo payOrderVo){
        PayOrderVo order = payOrderAccountService.order(payOrderVo);
        return ResponseModel.SUCCESS(order);
    }


}