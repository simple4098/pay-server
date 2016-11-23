package com.yql.biz.client;

import com.yql.biz.vo.pay.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 断路器实现类
 * @author simple
 */
@Component
public class PayClientHystrix implements PayClient {
    private static  final Logger logger = LoggerFactory.getLogger(PayClientHystrix.class);

    @Override
    public BangResponse bangBank(@RequestParam(name = "message") String message, @RequestParam("signature") String signature) {
        logger.debug("==========================bangBank支付断路器==========================");
        return null;
    }

    @Override
    public UninstallBangResponse uninstallBangBank(@RequestParam(name = "message") String message, @RequestParam("signature") String signature) {
        logger.debug("==========================uninstallBangBank支付断路器==========================");
        return null;
    }

    @Override
    public PayMessageValidateResponse pay(@RequestParam(name = "message") String message, @RequestParam("signature") String signature) {
        logger.debug("==========================pay支付断路器==========================");
        return PayMessageValidateResponse.toBean();
    }

}
