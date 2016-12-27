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
import com.yql.core.web.ResponseModel;
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
public class PayOrderBalanceCreator implements IPayOrderCreator {
    private static final Logger logger = LoggerFactory.getLogger(PayOrderBalanceCreator.class);

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
        logger.debug("余额支付:"+ json);
        //支付验证支付密码
        PayAccount payAccount = payAccountDao.findByUserCode(payOrderVo.getUserCode());
        payPasswordSecurityHelper.validateOldPassword(payOrderVo.getPayPassword(),payAccount);
        //余额支付 调用accounting接口
        ResponseModel responseModel = accountClient.payment(payOrderVo.getTotalPrice(),payOrderVo.getOrderNo(), payOrderVo.getPayeeCode(),payOrderVo.getPayerCode(),null);
        logger.debug("accounting response :"+JSON.toJSONString(responseModel));
        String payNo = orderNoGenerator.generate(payOrderVo.getPayType());
        String diamondCode = orderNoGenerator.generateBalanceDiamondCode(payAccount, PayType.ACCOUNT);
        payOrderVo.setPayNo(payNo);
        payOrderVo.setTxCode(diamondCode);
        payOrderVo.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
        if (!responseModel.isSuccess()) {
            throw new RuntimeException(responseModel.getMessage());
        }
        logger.debug("余额支付成功:"+ payOrderVo.getOrderNo());
        return payOrderVo;

    }

    @Override
    public boolean supports(PayOrderVo payOrderVo) {
        return PayType.ACCOUNT.equals(payOrderVo.getPayType());
    }
}
