package com.yql.biz.support.pay;

import com.alibaba.fastjson.JSON;
import com.yql.biz.enums.PayType;
import com.yql.biz.enums.pay.PayStatus;
import com.yql.biz.support.OrderNoGenerator;
import com.yql.biz.vo.PayOrderVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> 微信支付 </p>
 *
 * @auther simple
 * data 2016/11/25 0025.
 */
@Service
public class PayOrderWxPayCreator implements IPayOrderCreator {
    private static final Logger logger = LoggerFactory.getLogger(PayOrderWxPayCreator.class);

  /*  @Resource
    private IPayOrderParamHelper payOrderCardParamHelper;
    @Resource
    private ApplicationConf applicationConf;
    @Resource
    private IWxPayClient wxPayClient;*/
    @Resource
    private OrderNoGenerator orderNoGenerator;

    @Override
    public PayOrderVo transform(PayOrderVo payOrderVo) {
        logger.debug("微信订单支付:"+ JSON.toJSONString(payOrderVo));
        String payNo = orderNoGenerator.generate(payOrderVo.getPayType());
        payOrderVo.setPayNo(payNo);
        payOrderVo.setPayStatus(PayStatus.WX_PAY_UNIFIED_ORDER.getValue());
        return payOrderVo;
    }

    @Override
    public boolean supports(PayOrderVo payOrderVo) {
        return PayType.WX_PAY.equals(payOrderVo.getPayType());
    }
}
