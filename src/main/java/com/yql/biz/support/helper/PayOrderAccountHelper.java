package com.yql.biz.support.helper;

import com.yql.biz.support.OrderNoGenerator;
import com.yql.biz.vo.PayOrderVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p>余额支付</p>
 * creator simple
 * data 2016/11/11 0011.
 */
@Component
public class PayOrderAccountHelper implements IPayOrderAccountHelper {
    private static final Logger logger = LoggerFactory.getLogger(PayOrderAccountHelper.class);

    @Resource
    private OrderNoGenerator orderNoGenerator;


    @Override
    public PayOrderVo orderType(PayOrderVo payOrderVo) {
        logger.debug("余额支付...............");
        long payNo = orderNoGenerator.generate(payOrderVo.getPayType());
        //payClient.pay("test","test");
        payOrderVo.setPayStatus(true);
        payOrderVo.setPayNo(payNo);
        payOrderVo.setPayOrder(07551236l);
        return payOrderVo;
    }
}
