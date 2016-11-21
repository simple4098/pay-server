package com.yql.biz.support.helper;

import com.yql.biz.client.IAccountClient;
import com.yql.biz.exception.MessageRuntimeException;
import com.yql.biz.support.OrderNoGenerator;
import com.yql.biz.vo.AccountVo;
import com.yql.biz.vo.PayOrderVo;
import com.yql.biz.web.ResponseModel;
import com.yql.framework.mq.MessagePublisher;
import com.yql.framework.mq.model.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;

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
    @Resource
    private IAccountClient accountClient;
    @Resource
    private MessagePublisher messagePublisher;


    @Override
    public PayOrderVo orderType(PayOrderVo payOrderVo) {
        ResponseModel<AccountVo> account = accountClient.getAccount(payOrderVo.getUserCode());
        AccountVo data = account.getData();
        if (data!=null ){
            BigDecimal cashFee = data.getCashFee();
            BigDecimal totalPrice = payOrderVo.getTotalPrice();
            if (cashFee!=null && cashFee.subtract(totalPrice).doubleValue()<0) throw new MessageRuntimeException("error.payserver.account.balance");
            messagePublisher.send(new TextMessage("TEST_USER_REGISTER","PAY-SERVER-TAG","pay-key-123","余额支付 PayOrderCardHelper test 支付系统"));
            logger.debug("余额支付...............");
            long payNo = orderNoGenerator.generate(payOrderVo.getPayType());
            //payClient.pay("test","test");
            payOrderVo.setPayStatus(true);
            payOrderVo.setPayNo(payNo);
            payOrderVo.setPayOrder(07551236l);
            return payOrderVo;
        }else {
            throw new MessageRuntimeException("error.payserver.paypassword");
        }

    }
}
