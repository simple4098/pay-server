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
     * 支付宝回调
     * @return
     */
     @RequestMapping("/ali_pay")
    public ResponseModel drawMoney(){



        return ResponseModel.SUCCESS();
    }


}
