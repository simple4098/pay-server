package com.yql.biz.support.helper;

import com.yql.biz.support.OrderNoGenerator;
import com.yql.biz.vo.PayOrderVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p>银行卡支付</p>
 * creator simple
 * data 2016/11/11 0011.
 */
@Component
public class PayOrderCardHelper implements IPayOrderAccountHelper {
    private static final Logger loger = LoggerFactory.getLogger(PayOrderCardHelper.class);
    @Resource
    private OrderNoGenerator orderNoGenerator;

    @Override
    public PayOrderVo orderType(PayOrderVo payOrderVo) {
        loger.debug("银行卡支付............");
        long payNo = orderNoGenerator.generate(payOrderVo.getPayType());
        payOrderVo.setPayStatus(true);
        payOrderVo.setPayNo(payNo);
        payOrderVo.setPayOrder(07551266656515l);
        return payOrderVo;
    }
}
