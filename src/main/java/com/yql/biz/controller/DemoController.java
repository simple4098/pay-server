package com.yql.biz.controller;

import com.yql.biz.client.ComputeClient;
import com.yql.biz.model.Order;
import com.yql.biz.service.IPayService;
import com.yql.biz.web.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p> 模拟调用第三方支付接口 </p>
 * @auther simple
 * data 2016/11/14 0014.
 */
@RestController
public class DemoController {
    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);
    @Resource
    private IPayService payService;
    @Resource
    private ComputeClient computeClient;
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public ResponseModel pongMessage( Integer input) {
        Order pay = payService.pay(input);
        return ResponseModel.SUCCESS(pay);
    }

    @RequestMapping("/add-feign")
    public Integer addTest(@RequestParam Integer a,@RequestParam Integer b){
        return computeClient.add(a,b);
    }

    @RequestMapping(value = "/add" ,method = RequestMethod.GET)
    public Integer add(@RequestParam Integer a, @RequestParam Integer b) {
        ServiceInstance instance = discoveryClient.getLocalServiceInstance();
        Integer r = a + b;
        logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + r);
        return r;
    }
}
