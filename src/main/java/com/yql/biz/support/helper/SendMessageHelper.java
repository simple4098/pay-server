package com.yql.biz.support.helper;

import com.alibaba.fastjson.JSON;
import com.yql.biz.conf.ApplicationConf;
import com.yql.biz.enums.SendMsgTag;
import com.yql.biz.enums.pay.PayStatus;
import com.yql.biz.enums.pay.WxPayResult;
import com.yql.biz.util.PayUtil;
import com.yql.biz.vo.PayOrderVo;
import com.yql.biz.vo.ResultPayOrder;
import com.yql.biz.vo.pay.response.WeiXinResponseResult;
import com.yql.biz.vo.pay.wx.WeiXinNotifyVo;
import com.yql.framework.mq.MessagePublisher;
import com.yql.framework.mq.model.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * <p> 发消息 </p>
 * @auther simple
 * data 2016/11/30 0030.
 */
@Component
public class SendMessageHelper {
    private static final Logger log = LoggerFactory.getLogger(SendMessageHelper.class);
    @Resource
    private ApplicationConf applicationConf;
    @Resource
    private MessagePublisher messagePublisher;


    /**
     * 基本发消息方法
     * @param textMessage
     */
    public void sendMessage(TextMessage textMessage){
        boolean open = applicationConf.isOpen();
        log.debug("消息开关:\t"+open+"================textMessage====================\n"+JSON.toJSONString(textMessage));
        if (open){
            log.debug("============发送消息 start=============");
            messagePublisher.send(textMessage);
        }
    }

    /**
     *  微信异步通知发消息
     * @param resultPayOrder 订单基本数据
     * @param outTradeNo 订单号
     */
    public void sendWxNotify(ResultPayOrder resultPayOrder, String outTradeNo) {
        resultPayOrder.setPayStatus(PayStatus.HANDLING.getValue());
        TextMessage textMessage =  new TextMessage(applicationConf.getSendMsgTopic(),  SendMsgTag.PAY_SERVER_STATUS.name(),outTradeNo,resultPayOrder);
        sendMessage(textMessage);
    }

    /**
     * 微信异步通知，处理业务成功失败 发送消息
     * @param weiXinResponse 返回微信对象
     * @param resultPayOrder 订单基本信息
     * @param weiXinNotifyVo 微信异步通知的参数值
     */
    public void sendWxNotifyResult(WeiXinResponseResult weiXinResponse, ResultPayOrder resultPayOrder, WeiXinNotifyVo weiXinNotifyVo) {
        TextMessage textMessage = null;
        if (weiXinNotifyVo.getResultCode().equals(WxPayResult.SUCCESS.name())){
            weiXinResponse.setReturnCode(WxPayResult.SUCCESS);
            BigDecimal totalFee = PayUtil.centToPrice(Integer.valueOf(weiXinNotifyVo.getTotalFee()));
            resultPayOrder.setPayPrice(totalFee);
            resultPayOrder.setTxCode(weiXinNotifyVo.getOpenid());
            resultPayOrder.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
            textMessage = new TextMessage(applicationConf.getSendMsgTopic(),  SendMsgTag.PAY_SERVER_STATUS.name(),weiXinNotifyVo.getOutTradeNo(),resultPayOrder);
        }else {
            weiXinResponse.setReturnCode(WxPayResult.FAIL);
            weiXinResponse.setReturnMsg("微信异步通知,处理业务失败");
            resultPayOrder.setPayStatus(PayStatus.PAY_UNSUCCESS.getValue());
            weiXinResponse.setReturnCode(WxPayResult.FAIL);
            textMessage = new TextMessage(applicationConf.getSendMsgTopic(),  SendMsgTag.PAY_SERVER_STATUS.name(),weiXinNotifyVo.getOutTradeNo(),resultPayOrder);
        }
        sendMessage(textMessage);
    }

    /**
     * 提现发送消息
     * @param payOrderVo 提现订单信息
     */
    public void sendDrawMoney(PayOrderVo payOrderVo) {
        ResultPayOrder resultPayOrder = PayOrderVo.toResultOrder(payOrderVo);
        TextMessage textMessage =  new TextMessage(applicationConf.getSendMsgTopic(),  SendMsgTag.PAY_SERVER_STATUS.name(),payOrderVo.getOrderNo(),resultPayOrder);
        sendMessage(textMessage);

    }
}
