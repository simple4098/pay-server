package com.yql.biz.service.impl;

import com.alibaba.fastjson.JSON;
import com.yql.biz.conf.ApplicationConf;
import com.yql.biz.dao.IPayOrderAccountDao;
import com.yql.biz.dao.IPayOrderAccountDetailDao;
import com.yql.biz.enums.SendMsgTag;
import com.yql.biz.enums.pay.WxPayResult;
import com.yql.biz.model.PayAccount;
import com.yql.biz.model.PayOrderAccount;
import com.yql.biz.model.PayOrderAccountDetail;
import com.yql.biz.service.IPayOrderAccountService;
import com.yql.biz.support.helper.IPayAccountServiceHelper;
import com.yql.biz.support.helper.IPayOrderParamHelper;
import com.yql.biz.support.helper.SendMessageHelper;
import com.yql.biz.support.pay.PayOrderCreatorComposition;
import com.yql.biz.util.PayUtil;
import com.yql.biz.vo.PayOrderAccountDetailVo;
import com.yql.biz.vo.PayOrderVo;
import com.yql.biz.vo.ResultPayOrder;
import com.yql.biz.vo.pay.response.WeiXinResponse;
import com.yql.biz.vo.pay.response.WeiXinResponseResult;
import com.yql.biz.vo.pay.wx.ResponseHandler;
import com.yql.biz.vo.pay.wx.WeiXinNotifyVo;
import com.yql.framework.mq.model.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.SortedMap;

/**
 * <p>支付订单号</p>
 * creator simple
 * data 2016/11/10 0010.
 */
@Service
public class PayOrderAccountService implements IPayOrderAccountService {
    private static final Logger log = LoggerFactory.getLogger(PayOrderAccountService.class);
    @Resource
    private IPayOrderAccountDao payOrderAccountDao;
    @Resource
    private IPayOrderAccountDetailDao payOrderAccountDetailDao;
    @Resource
    private IPayAccountServiceHelper payAccountServiceHelper;
    @Resource
    private ApplicationConf applicationConf;
    @Resource(name ="payOrderCreatorComposition")
    private PayOrderCreatorComposition payOrderCreator;
    @Resource
    private IPayOrderParamHelper payOrderParamHelper;
    @Resource
    private SendMessageHelper sendMessageHelper;

    @Override
    public ResultPayOrder order(PayOrderVo payOrderVo) {
        log.info("pay-server param:" + JSON.toJSONString(payOrderVo));
        PayAccount payAccount = payAccountServiceHelper.findOrCratePayAccount(payOrderVo.getUserCode());
        PayOrderAccount orderAccount = payOrderAccountDao.findByOrderNo(payOrderVo.getOrderNo());
        PayOrderAccount payOrderAccount = PayOrderVo.toDomain(payOrderVo);
        payOrderAccount.setPayAccountId(payAccount.getId());
        PayOrderVo orderVo = payOrderCreator.transform(payOrderVo);
        payOrderAccount.setPayStatus(orderVo.getPayStatus());
        payOrderAccount.setPayOrder(orderVo.getPayOrder());
        payOrderAccount.setMsg(orderVo.getMsg());
        payOrderAccount.setPayNo(orderVo.getPayNo());
        payOrderAccount.setPayBankId(orderVo.getPayBankId());
        PayOrderAccountDetail payOrderAccountDetail = PayOrderAccountDetailVo.toDomain(payOrderAccount);
        if (orderAccount != null) {
            payOrderAccount.setId(orderAccount.getId());
            payOrderAccount.setVersion(orderAccount.getVersion());
        }
        PayOrderAccount result = payOrderAccountDao.save(payOrderAccount);
        payOrderAccountDetail.setPayOrderAccountId(result.getId());
        payOrderAccountDetailDao.save(payOrderAccountDetail);
        //ResultPayOrder payOrder = PayOrderVo.toResultOrder(payOrderVo);
        ResultPayOrder payOrder = PayOrderVo.toResultOrder(payOrderVo);
        return payOrder;
    }


    @Override
    public WeiXinResponseResult callPayNotify(ResponseHandler responseHandler) {
        log.debug("==============debug==========");
        WeiXinResponseResult weiXinResponse = new WeiXinResponseResult();
        responseHandler.setKey(applicationConf.getWxKey());
        SortedMap allParameters = responseHandler.getAllParameters();
        WeiXinNotifyVo weiXinNotifyVo = payOrderParamHelper.getWxCallBackParam(allParameters);
        ResultPayOrder resultPayOrder = new ResultPayOrder();
        resultPayOrder.setPayStatus(10);
        String json = JSON.toJSONString(resultPayOrder);
        TextMessage textMessage =  new TextMessage(applicationConf.getSendMsgTopic(),  SendMsgTag.PAY_SERVER_WX_CALLBACK.name(),weiXinNotifyVo.getOutTradeNo(),json);
        sendMessageHelper.sendMessage(textMessage);
        if (WxPayResult.SUCCESS.name().equals(weiXinNotifyVo.getResultCode())){
            boolean tenpaySign = responseHandler.isTenpaySign();
            log.debug(" 微信异步通知: "+responseHandler.getDebugInfo());
            if (tenpaySign){
                if (weiXinNotifyVo.getResultCode().equals(WxPayResult.SUCCESS.name())){
                    weiXinResponse.setReturnCode(WxPayResult.SUCCESS);
                    BigDecimal totalFee = PayUtil.centToPrice(Integer.valueOf(weiXinNotifyVo.getTotalFee()));
                    resultPayOrder.setPayPrice(totalFee);
                    resultPayOrder.setPayOrder(weiXinNotifyVo.getTransactionId());
                    resultPayOrder.setPayStatus(20);
                    json = JSON.toJSONString(resultPayOrder);
                    textMessage.setBody(json.getBytes());
                }else {
                    resultPayOrder.setPayStatus(30);
                    json = JSON.toJSONString(resultPayOrder);
                    weiXinResponse.setReturnCode(WxPayResult.FAIL);
                    textMessage.setBody(json.getBytes());
                }
                sendMessageHelper.sendMessage(textMessage);
            }else {
                weiXinResponse.setReturnCode(WxPayResult.FAIL);
                weiXinResponse.setReturnMsg("签名失败");
            }
        }else {
            weiXinResponse.setReturnCode(WxPayResult.FAIL);
            weiXinResponse.setReturnMsg(weiXinNotifyVo.getResultCode());
        }
        return weiXinResponse;
    }
}
