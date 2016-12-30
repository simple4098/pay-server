package com.yql.biz.controller;

import com.yql.biz.constraint.OrderNo;
import com.yql.biz.service.IPayOrderAccountService;
import com.yql.core.web.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>支付退款请求</p>
 * @author  simple on 2016/11/3 0003.
 */
@RestController
@RequestMapping("/pay")
public class RefundController {
    private static  final Logger log = LoggerFactory.getLogger(RefundController.class);
    @Resource
    private IPayOrderAccountService payOrderAccountService;

    @RequestMapping("/refund")
    public ResponseModel refund(@OrderNo String orderNo){
        payOrderAccountService.refundOrder(orderNo);
        return ResponseModel.SUCCESS();
    }


}
