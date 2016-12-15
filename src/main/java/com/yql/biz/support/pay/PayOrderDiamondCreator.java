package com.yql.biz.support.pay;

import com.alibaba.fastjson.JSON;
import com.yql.biz.client.IAccountClient;
import com.yql.biz.dao.IPayAccountDao;
import com.yql.biz.enums.PayType;
import com.yql.biz.enums.pay.PayStatus;
import com.yql.biz.model.PayAccount;
import com.yql.biz.support.OrderNoGenerator;
import com.yql.biz.support.helper.PayPasswordSecurityHelper;
import com.yql.biz.vo.PayOrderVo;
import com.yql.biz.web.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p> 钻石支付 </p>
 * @auther simple
 * data 2016/11/23 0023.
 */
@Component
public class PayOrderDiamondCreator implements IPayOrderCreator {

    private static final Logger logger = LoggerFactory.getLogger(PayOrderDiamondCreator.class);

    @Resource
    private OrderNoGenerator orderNoGenerator;
    @Resource
    private IAccountClient accountClient;
    @Resource
    private PayPasswordSecurityHelper payPasswordSecurityHelper;
    @Resource
    private IPayAccountDao payAccountDao;


    @Override
    public PayOrderVo transform(PayOrderVo payOrderVo) {
        String json = JSON.toJSONString(payOrderVo);
        logger.debug("钻石支付:"+ json);
        //支付验证支付密码
        PayAccount payAccount = payAccountDao.findByUserCode(payOrderVo.getUserCode());
        payPasswordSecurityHelper.validateOldPassword(payOrderVo.getPayPassword(),payAccount);
        ResponseModel responseModel = accountClient.diamondTurnCash(payOrderVo.getTotalPrice(), payOrderVo.getUserCode(), payOrderVo.getOrderNo());
        if (responseModel.isSuccess()) {
            String payNo = orderNoGenerator.generate(payOrderVo.getPayType());
            String diamondCode = orderNoGenerator.generateBalanceDiamondCode(payAccount, PayType.DIAMOND);
            payOrderVo.setPayNo(payNo);
            payOrderVo.setTxCode(diamondCode);
            payOrderVo.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
            return payOrderVo;
        }else {
            throw new RuntimeException(responseModel.getMessage());
        }

    }

    @Override
    public boolean supports(PayOrderVo payOrderVo) {
        return PayType.DIAMOND.equals(payOrderVo.getPayType());
    }
}
