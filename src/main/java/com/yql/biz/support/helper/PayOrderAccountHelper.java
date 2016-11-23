package com.yql.biz.support.helper;

import com.alibaba.fastjson.JSON;
import com.yql.biz.client.IAccountClient;
import com.yql.biz.conf.ApplicationConf;
import com.yql.biz.enums.SendMsgTag;
import com.yql.biz.enums.pay.PayStatus;
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
    @Resource
    private ApplicationConf applicationConf;


    @Override
    public PayOrderVo orderType(PayOrderVo payOrderVo) {
        String json = JSON.toJSONString(payOrderVo);
        logger.debug("余额支付:"+ JSON.toJSONString(payOrderVo));
        ResponseModel<AccountVo> account = accountClient.getAccount(payOrderVo.getUserCode());
        AccountVo data = account.getData();
        if (data!=null ){
            BigDecimal cashFee = data.getCashFee();
            BigDecimal totalPrice = payOrderVo.getTotalPrice();
            TextMessage textMessage =  new TextMessage(applicationConf.getSendMsgTopic(),  SendMsgTag.PAY_SERVER_BALANCE_UNSUCCESS.name(), payOrderVo.getOrderNo().toString(),json.getBytes());
            if (cashFee!=null && cashFee.subtract(totalPrice).doubleValue()<0){
                payOrderVo.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
                textMessage = new TextMessage(applicationConf.getSendMsgTopic(),  SendMsgTag.PAY_SERVER_BALANCE_SUCCESS.name(), payOrderVo.getOrderNo().toString(),"");
                messagePublisher.send(textMessage);
                logger.debug("余额成功:"+ payOrderVo.getOrderNo());
            }else {
                messagePublisher.send(textMessage);
                logger.debug("余额失败:"+ payOrderVo.getOrderNo());
                throw new MessageRuntimeException("error.payserver.account.balance");
            }
            long payNo = orderNoGenerator.generate(payOrderVo.getPayType());
            payOrderVo.setPayNo(payNo);
            return payOrderVo;
        }else {
            throw new MessageRuntimeException("error.payserver.paypassword");
        }

    }
}
