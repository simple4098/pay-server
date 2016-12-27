package com.yql.biz.support.helper;

import com.alibaba.fastjson.JSON;
import com.yql.biz.conf.ApplicationConf;
import com.yql.biz.dao.IPayOrderAccountDao;
import com.yql.biz.enums.PayType;
import com.yql.biz.enums.SendMsgTag;
import com.yql.biz.enums.pay.PayStatus;
import com.yql.biz.model.PayOrderAccount;
import com.yql.biz.vo.ResultPayOrder;
import com.yql.biz.vo.SettlementResult;
import com.yql.framework.mq.model.MqMessage;
import com.yql.framework.mq.model.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * <p> 账户中心消息处理对象 </p>
 *
 * @auther simple
 * data 2016/11/22 0022.
 */
@Component
public class AccountMsgEventListener implements IAccountMsgEventListener {
    private static final Logger logger = LoggerFactory.getLogger(AccountMsgEventListener.class);
    @Resource
    private IPayOrderAccountDao payOrderAccountDao;
    @Resource
    private ApplicationConf applicationConf;
    @Resource
    private SendMessageHelper sendMessageHelper;

    @Override
    @Transactional
    public void eventHandling(MqMessage message) {
        String bodyAsText = message.getBodyAsText();
        SettlementResult settlementResult = JSON.parseObject(bodyAsText, SettlementResult.class);
        PayOrderAccount orderInfo = payOrderAccountDao.findByOrderNo(settlementResult.getOrderNo());
        logger.debug("余额/钻石支付 订单消息监听:"+JSON.toJSONString(orderInfo));
        if (orderInfo != null) {
            ResultPayOrder resultPayOrder = new ResultPayOrder();
            BigDecimal totalPrice = orderInfo.getTotalPrice();
            resultPayOrder.setPayPrice(totalPrice);
            resultPayOrder.setPayNo(orderInfo.getPayNo());
            resultPayOrder.setOrderNo(orderInfo.getOrderNo());
            if ("ERROR".equals(settlementResult.getStatus())) {
                resultPayOrder.setPayStatus(PayStatus.PAY_UNSUCCESS.getValue());
            } else {
                if (orderInfo.getPayType().equals(PayType.DRAW_MONEY)){
                    resultPayOrder.setPayStatus(PayStatus.HANDLING.getValue());
                }else {
                    resultPayOrder.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
                }
            }
            orderInfo.setPayStatus(resultPayOrder.getPayStatus());
            resultPayOrder.setTxCode(orderInfo.getTxCode());
            TextMessage textMessage = new TextMessage(applicationConf.getSendMsgTopic(), SendMsgTag.PAY_SERVER_STATUS.name(), settlementResult.getOrderNo(), JSON.toJSONString(resultPayOrder));
            sendMessageHelper.sendMessage(textMessage);
            if ("ERROR".equals(settlementResult.getStatus())) {
                payOrderAccountDao.save(orderInfo);
            }
        } else {
            logger.debug("监听消息查询不到订单信息:" + bodyAsText);
        }
    }
}
