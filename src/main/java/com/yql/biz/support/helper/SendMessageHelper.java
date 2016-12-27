package com.yql.biz.support.helper;

import com.alibaba.fastjson.JSON;
import com.yql.biz.conf.ApplicationConf;
import com.yql.biz.enums.SendMsgTag;
import com.yql.biz.enums.pay.PayStatus;
import com.yql.biz.enums.pay.WxPayResult;
import com.yql.biz.enums.pay.WxTradeState;
import com.yql.biz.model.PayOrderAccount;
import com.yql.biz.service.IPayOrderAccountService;
import com.yql.biz.support.pay.QueryOrderComposition;
import com.yql.biz.util.PayUtil;
import com.yql.biz.vo.PayOrderVo;
import com.yql.biz.vo.ResultPayOrder;
import com.yql.biz.vo.ResultQueryOrder;
import com.yql.biz.vo.pay.response.WeiXinResponseResult;
import com.yql.biz.vo.pay.wx.WeiXinNotifyVo;
import com.yql.framework.mq.MessagePublisher;
import com.yql.framework.mq.model.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.*;

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
    @Resource(name = "queryOrderComposition")
    private QueryOrderComposition queryOrderComposition;
    @Resource
    private IPayOrderAccountService payOrderAccountService;
    @Resource
    private IPayOrderAccountHelper payOrderAccountHelper;


    /**
     * 基本发消息方法
     * @param textMessage
     */
    public void sendMessage(TextMessage textMessage){
        boolean open = applicationConf.getOpenSendMessage();
        log.debug("消息开关:\t"+open+"================textMessage====================\n"+JSON.toJSONString(textMessage));
        if (open){
            log.debug("============发送消息 start=============");
            messagePublisher.send(textMessage);
        }
    }

    /**
     *  微信异步通知发消息
     * @param resultPayOrder 订单基本数据
     */
    public void sendWxNotify(ResultPayOrder resultPayOrder) {
        TextMessage textMessage =  new TextMessage(applicationConf.getSendMsgTopic(),  SendMsgTag.PAY_SERVER_STATUS.name(),resultPayOrder.getOrderNo(),resultPayOrder);
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
            textMessage = new TextMessage(applicationConf.getSendMsgTopic(),  SendMsgTag.PAY_SERVER_STATUS.name(),resultPayOrder.getOrderNo(),resultPayOrder);
        }else {
            weiXinResponse.setReturnCode(WxPayResult.FAIL);
            weiXinResponse.setReturnMsg("微信异步通知,处理业务失败");
            resultPayOrder.setPayStatus(PayStatus.PAY_UNSUCCESS.getValue());
            weiXinResponse.setReturnCode(WxPayResult.FAIL);
            textMessage = new TextMessage(applicationConf.getSendMsgTopic(),  SendMsgTag.PAY_SERVER_STATUS.name(),resultPayOrder.getOrderNo(),resultPayOrder);
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

    /**
     * 定时发送第三方平台的订单状态
     * @param list 订单集合
     */
    public  void timeCheckOrderStatus(List<PayOrderAccount> list){
        if (!CollectionUtils.isEmpty(list)){
            Integer threadNum = applicationConf.getThreadNum();
            ExecutorService es = Executors.newFixedThreadPool(threadNum);
            CompletionService cs = new ExecutorCompletionService(es);
            for (PayOrderAccount payOrderAccount:list){
                cs.submit(getTask(payOrderAccount));
            }
            es.shutdown();
        }
    }

    private Callable getTask(PayOrderAccount orderAccount) {
        return (Callable) () -> {
            ResultQueryOrder wxQueryOrder = queryOrderComposition.transform(orderAccount);
            log.debug("第三方支付平台订单信息:"+JSON.toJSONString(wxQueryOrder));
            if (wxQueryOrder!=null && wxQueryOrder.getWxTradeState()!=null){
                ResultPayOrder resultPayOrder = new ResultPayOrder();
                resultPayOrder.setOrderNo(orderAccount.getOrderNo());
                resultPayOrder.setPayNo(orderAccount.getPayNo());
                resultPayOrder.setPayType(orderAccount.getPayType());
                resultPayOrder.setTxCode(orderAccount.getTxCode());
                resultPayOrder.setPayPrice(orderAccount.getTotalPrice());
                //支付成功
                if (wxQueryOrder.getWxTradeState().equals(WxTradeState.SUCCESS)){
                    if (!orderAccount.getPayStatus().equals(PayStatus.PAY_SUCCESS.getValue())){
                        //订单状态实际为成功，但是系统的订单状态不成功，要更新成支付成功 并发通知
                        resultPayOrder.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
                        orderAccount.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
                        payOrderAccountHelper.saveOrder(orderAccount);
                        sendWxNotify(resultPayOrder);
                    }
                    //未支付
                }else if (wxQueryOrder.getWxTradeState().equals(WxTradeState.NOTPAY)){
                    orderAccount.setPayStatus(PayStatus.NOT_PAY.getValue());
                    payOrderAccountHelper.saveOrder(orderAccount);
                    resultPayOrder.setPayStatus(PayStatus.NOT_PAY.getValue());
                    sendWxNotify(resultPayOrder);
                    //支付失败
                }else if (wxQueryOrder.getWxTradeState().equals(WxTradeState.PAYERROR)){
                    if (!orderAccount.getPayStatus().equals(PayStatus.PAY_UNSUCCESS.getValue())){
                        orderAccount.setPayStatus(PayStatus.PAY_UNSUCCESS.getValue());
                        payOrderAccountHelper.saveOrder(orderAccount);
                        resultPayOrder.setPayStatus(PayStatus.PAY_UNSUCCESS.getValue());
                        sendWxNotify(resultPayOrder);
                    }
                }
            }
            return null;
        };
    }
}
