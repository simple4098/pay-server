package com.yql.biz.controller;

import com.yql.biz.service.IPayOrderAccountService;
import com.yql.biz.util.PayUtil;
import com.yql.core.web.ResponseModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

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
    public String payNotify() {
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
     * ali-pay回调
     */
    @RequestMapping("/ali-pay")
    public void aliPay(HttpServletRequest request, HttpServletResponse response){
        payOrderAccountService.callAliPayNotify(request,response);
    }

    /**
     * ali-pay return url
     */
    @RequestMapping("/ali-pay-return")
    public String ali(HttpServletRequest request){
        Map<String, String> notityXml = PayUtil.toMap(request);
        System.out.println("ali-pay-return "+notityXml);
        return "支付成功:success ali-pay-return";
    }


}
