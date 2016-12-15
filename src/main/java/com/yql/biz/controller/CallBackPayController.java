package com.yql.biz.controller;

import com.yql.biz.service.IPayOrderAccountService;
import com.yql.biz.web.ResponseModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>渠道异步通知</p>
 * @author  simple on 2016/11/3 0003.
 */
@RestController
@RequestMapping("/pay")
public class CallBackPayController {

    @Resource
    private IPayOrderAccountService payOrderAccountService;


    /**
     * 微信回调
     */
    @RequestMapping("/wx")
    public String wx(HttpServletRequest request){
        return payOrderAccountService.callPayNotify(request);
    }

    /**
     * 富友回调
     */
    @RequestMapping("/fy")
    public ResponseModel fy(HttpServletRequest request) {
        payOrderAccountService.callFyPayNotify(request);
        return ResponseModel.SUCCESS();
    }

    /**
     * fy页面跳转
     */
    @RequestMapping("/fy/page-notify-url")
    public String payNotify(HttpServletRequest request) {
        return "支付成功:success";
    }

    /**
     * fy支付失败
     */
    @RequestMapping("/fy/h5reurl")
    public String h5reurl(HttpServletRequest request) {
        return "支付失败";
    }

    /**
     * 支付宝回调
     * @return
     */
     @RequestMapping("/ali_pay")
    public ResponseModel drawMoney(){



        return ResponseModel.SUCCESS();
    }


}
