package com.yql.biz.controller;

import com.yql.biz.service.IPayOrderAccountService;
import com.yql.biz.util.PayUtil;
import com.yql.biz.vo.PayOrderVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * <p> 手机wap端支付 </p>
 * @auther simple
 * data 2016/11/14 0014.
 */
@Controller
public class WapPayController {
    private static final Logger logger = LoggerFactory.getLogger(WapPayController.class);
    @Resource
    private IPayOrderAccountService payOrderAccountService;

    @RequestMapping(value="/ali-wap-pay")
    public void aliWapPay(HttpServletResponse response,PayOrderVo payOrderVo){
        String wapFrom = payOrderAccountService.aliWapFrom(payOrderVo);
        PayUtil.toResponse(response,wapFrom);
    }


}
