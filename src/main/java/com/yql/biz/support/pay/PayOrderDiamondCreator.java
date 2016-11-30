package com.yql.biz.support.pay;

import com.alibaba.fastjson.JSON;
import com.yql.biz.client.IAccountClient;
import com.yql.biz.conf.ApplicationConf;
import com.yql.biz.enums.PayType;
import com.yql.biz.enums.SendMsgTag;
import com.yql.biz.enums.pay.PayStatus;
import com.yql.biz.support.OrderNoGenerator;
import com.yql.biz.support.helper.SendMessageHelper;
import com.yql.biz.vo.AccountVo;
import com.yql.biz.vo.PayOrderVo;
import com.yql.biz.vo.ResultPayOrder;
import com.yql.biz.web.ResponseModel;
import com.yql.framework.mq.MessagePublisher;
import com.yql.framework.mq.model.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;

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
    private SendMessageHelper sendMessageHelper;
    @Resource
    private ApplicationConf applicationConf;


    @Override
    public PayOrderVo transform(PayOrderVo payOrderVo) {
        String json = JSON.toJSONString(payOrderVo);
        logger.debug("钻石支付:"+ JSON.toJSONString(payOrderVo));
        ResponseModel<AccountVo> account = accountClient.getAccount(payOrderVo.getUserCode());
        AccountVo data = account.getData();
        if (data!=null ){
            BigDecimal cashFee = data.getDiamondFee();
            BigDecimal totalPrice = payOrderVo.getTotalPrice();
            long payNo = orderNoGenerator.generate(payOrderVo.getPayType());
            payOrderVo.setPayNo(payNo);
            ResultPayOrder resultPayOrder = new ResultPayOrder();
            resultPayOrder.setPayPrice(totalPrice);
            resultPayOrder.setPayNo(payNo);
            resultPayOrder.setOrderNo(payOrderVo.getOrderNo());
            resultPayOrder.setPayStatus(PayStatus.PAY_UNSUCCESS.getValue());
            TextMessage textMessage = new TextMessage(applicationConf.getSendMsgTopic(), SendMsgTag.PAY_SERVER_STATUS.name(), payOrderVo.getOrderNo(), JSON.toJSONString(resultPayOrder));
            if (cashFee!=null && cashFee.subtract(totalPrice).doubleValue()<0){
                resultPayOrder.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
                payOrderVo.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
                textMessage = new TextMessage(applicationConf.getSendMsgTopic(),  SendMsgTag.PAY_SERVER_STATUS.name(), payOrderVo.getOrderNo(),JSON.toJSONString(resultPayOrder));
                sendMessageHelper.sendMessage(textMessage);
                logger.debug("钻石支付成功:"+ payOrderVo.getOrderNo());
            }else {
                sendMessageHelper.sendMessage(textMessage);
                logger.info("钻石支付失败 订单号【"+payOrderVo.getOrderNo()+"】");
                throw new RuntimeException(" 钻石支付失败 订单号【"+payOrderVo.getOrderNo()+"】");
            }
            return payOrderVo;
        }else {
            throw new RuntimeException(account.getMessage());
        }

    }

    @Override
    public boolean supports(PayOrderVo payOrderVo) {
        return PayType.DIAMOND.equals(payOrderVo.getPayType());
    }
}
