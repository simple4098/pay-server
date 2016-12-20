package com.yql.biz.support.pay;

import com.alibaba.fastjson.JSON;
import com.yql.biz.client.IAccountClient;
import com.yql.biz.conf.ApplicationConf;
import com.yql.biz.dao.IPayAccountDao;
import com.yql.biz.enums.PayType;
import com.yql.biz.enums.SendMsgTag;
import com.yql.biz.enums.pay.PayStatus;
import com.yql.biz.model.PayAccount;
import com.yql.biz.support.OrderNoGenerator;
import com.yql.biz.support.helper.PayPasswordSecurityHelper;
import com.yql.biz.support.helper.SendMessageHelper;
import com.yql.biz.vo.AccountVo;
import com.yql.biz.vo.PayOrderVo;
import com.yql.biz.vo.ResultPayOrder;
import com.yql.biz.web.ResponseModel;
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
public class PayOrderBalanceCreator implements IPayOrderCreator {
    private static final Logger logger = LoggerFactory.getLogger(PayOrderBalanceCreator.class);

    @Resource
    private OrderNoGenerator orderNoGenerator;
    @Resource
    private IAccountClient accountClient;
    @Resource
    private SendMessageHelper sendMessageHelper;
    @Resource
    private ApplicationConf applicationConf;
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
        ResponseModel<AccountVo> account = accountClient.getAccount(payOrderVo.getUserCode());
        AccountVo data = account.getData();
        if (data!=null ){
            BigDecimal cashFee = data.getCashFee();
            BigDecimal totalPrice = payOrderVo.getTotalPrice();
            String payNo = orderNoGenerator.generate(payOrderVo.getPayType());
            String diamondCode = orderNoGenerator.generateBalanceDiamondCode(payAccount, PayType.ACCOUNT);
            payOrderVo.setTxCode(diamondCode);
            payOrderVo.setPayNo(payNo);
            ResultPayOrder resultPayOrder = new ResultPayOrder();
            resultPayOrder.setPayPrice(totalPrice);
            resultPayOrder.setPayNo(payNo);
            resultPayOrder.setOrderNo(payOrderVo.getOrderNo());
            resultPayOrder.setPayStatus(PayStatus.PAY_UNSUCCESS.getValue());
            TextMessage textMessage =  new TextMessage(applicationConf.getSendMsgTopic(),  SendMsgTag.PAY_SERVER_STATUS.name(),payOrderVo.getOrderNo() ,JSON.toJSONString(resultPayOrder));
            if (cashFee!=null && cashFee.compareTo(totalPrice)>-1){
                resultPayOrder.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
                resultPayOrder.setTxCode(diamondCode);
                payOrderVo.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
                textMessage = new TextMessage(applicationConf.getSendMsgTopic(),  SendMsgTag.PAY_SERVER_STATUS.name(), payOrderVo.getOrderNo(),JSON.toJSONString(resultPayOrder));
                sendMessageHelper.sendMessage(textMessage);
                logger.debug("余额成功:"+ payOrderVo.getOrderNo());
            }else {
                sendMessageHelper.sendMessage(textMessage);
                logger.error("余额不足 订单号【"+payOrderVo.getOrderNo()+"】");
                throw new RuntimeException("余额不足");
            }
            return payOrderVo;
        }else {
            throw new RuntimeException(account.getMessage());
        }

    }

    @Override
    public boolean supports(PayOrderVo payOrderVo) {
        return PayType.ACCOUNT.equals(payOrderVo.getPayType());
    }
}
