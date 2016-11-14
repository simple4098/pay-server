package com.yql.biz.controller;

import com.yql.biz.model.Order;
import com.yql.biz.service.IPayService;
import com.yql.biz.web.ResponseModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p> 模拟调用第三方支付接口 </p>
 * @auther simple
 * data 2016/11/14 0014.
 */
@RestController
public class DemoController {

    @Resource
    private IPayService payService;

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public ResponseModel pongMessage( Integer input) {
        Order pay = payService.pay(input);
        return ResponseModel.SUCCESS(pay);
    }
}
