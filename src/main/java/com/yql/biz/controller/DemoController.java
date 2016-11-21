package com.yql.biz.controller;

import com.yql.biz.client.ComputeClient;
import com.yql.biz.client.IUserCenterClient;
import com.yql.biz.service.IPayService;
import com.yql.biz.vo.UserBasicInfoVo;
import com.yql.biz.vo.pay.Param;
import com.yql.biz.vo.pay.request.BangBody;
import com.yql.biz.vo.pay.request.Head;
import com.yql.biz.vo.pay.request.Request;
import com.yql.biz.vo.pay.response.Response;
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
    @Resource
    private IUserCenterClient userCenterClient;

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public ResponseModel pongMessage( String userCode) {
        userCenterClient.getBaseUserInfo(userCode);
        ResponseModel<UserBasicInfoVo> responseModel = null;
        responseModel.success(userBasicInfoVo -> {
            logger.debug("======success======");

        });
        return responseModel;
    }

    @RequestMapping("/add-feign")
    public ResponseModel addTest(){
        Request<BangBody> request1 = new Request<>();
        Head head = new Head() ;
        head.setInstitutionID("sdsdsdsd111");
        head.setTxCode("1245450");
        request1.setHead(head);
        BangBody bangBody = new BangBody();
        bangBody.setAccountName("张琳");
        request1.setBody(bangBody);
        Param param = new Param();
        param.setMessage("lin");
        param.setSignature("zhanglin");
        //ComputeClient computeClient =  Feign.builder().target(ComputeClient.class, "http://localhost:8080/");
        //Response response = computeClient.add1(param);
        Response response1 = computeClient.add2("hello","zhangLin");
        System.out.println("==========="+response1);
     /*   Integer add = computeClient.add(1, 10);
        System.out.println("=========="+add);*/
        return ResponseModel.SUCCESS(response1);
    }

    @RequestMapping(value = "/add" ,method = RequestMethod.GET)
    public Integer add(@RequestParam Integer a, @RequestParam Integer b) {
        ServiceInstance instance = discoveryClient.getLocalServiceInstance();
        Integer r = a + b;
        logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + r);
        return r;
    }
}
