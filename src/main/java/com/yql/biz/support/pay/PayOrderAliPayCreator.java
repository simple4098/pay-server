package com.yql.biz.support.pay;

import com.alibaba.fastjson.JSON;
import com.yql.biz.enums.PayType;
import com.yql.biz.enums.pay.PayStatus;
import com.yql.biz.model.PayOrderAccount;
import com.yql.biz.support.OrderNoGenerator;
import com.yql.biz.support.helper.IPayOrderAccountHelper;
import com.yql.biz.vo.PayOrderVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> 支付宝支付 </p>
 *
 * @auther simple
 * data 2016/11/25 0025.
 */
@Service
public class PayOrderAliPayCreator implements IPayOrderCreator {
    private static final Logger logger = LoggerFactory.getLogger(PayOrderAliPayCreator.class);
    @Resource
    private OrderNoGenerator orderNoGenerator;
    @Resource
    private IPayOrderAccountHelper payOrderAccountHelper;

    @Override
    public PayOrderVo transform(PayOrderVo payOrderVo) {
        logger.debug("ali apy订单支付:"+ JSON.toJSONString(payOrderVo));
        String payNo = orderNoGenerator.generate(payOrderVo.getPayType());
        payOrderVo.setPayNo(payNo);
        payOrderVo.setPayStatus(PayStatus.HANDLING.getValue());
        PayOrderAccount payOrderAccount = PayOrderVo.toDomain(payOrderVo);
        payOrderAccountHelper.saveOrderTransform(payOrderAccount,payOrderVo.getUserCode());
        return payOrderVo;
    }

    @Override
    public boolean supports(PayOrderVo payOrderVo) {
        return PayType.ALI_PAY.equals(payOrderVo.getPayType());
    }
}
