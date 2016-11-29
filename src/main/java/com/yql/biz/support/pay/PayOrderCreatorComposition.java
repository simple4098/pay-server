package com.yql.biz.support.pay;

import com.yql.biz.vo.PayOrderVo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author wangxiaohong
 */
@Component
public class PayOrderCreatorComposition {

    @Resource
    private IPayOrderCreator[] orderCreators;

    public PayOrderVo transform(PayOrderVo payOrderVo) {
        for (IPayOrderCreator iPayOrderCreator : orderCreators) {
            if (iPayOrderCreator.supports(payOrderVo)) {
                return iPayOrderCreator.transform(payOrderVo);
            }
        }
        throw new UnsupportedOperationException("No suitable PayOrderCreator!");
    }
}