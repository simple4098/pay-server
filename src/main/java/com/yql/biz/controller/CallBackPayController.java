package com.yql.biz.controller;

import com.yql.biz.service.IPayOrderAccountService;
import com.yql.biz.vo.PayOrderVo;
import com.yql.biz.vo.pay.response.WeiXinResponse;
import com.yql.biz.vo.pay.response.WeiXinResponseResult;
import com.yql.biz.vo.pay.wx.ResponseHandler;
import com.yql.biz.web.ResponseModel;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    @RequestMapping(value = "/wx",method = RequestMethod.POST, produces = { "application/xml", "text/xml" }, consumes = MediaType.ALL_VALUE)
    public WeiXinResponseResult wx(HttpServletRequest request, HttpServletResponse response){
        ResponseHandler responseHandler = new ResponseHandler(request,response);
        WeiXinResponseResult weiXinResponse = payOrderAccountService.callPayNotify(responseHandler);
        return weiXinResponse;
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
