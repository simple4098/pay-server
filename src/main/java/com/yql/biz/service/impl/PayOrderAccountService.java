package com.yql.biz.service.impl;

import com.alibaba.fastjson.JSON;
import com.yql.biz.conf.ApplicationConf;
import com.yql.biz.dao.IPayOrderAccountDao;
import com.yql.biz.dao.IPayOrderAccountDetailDao;
import com.yql.biz.enums.pay.PayStatus;
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
import com.yql.biz.vo.pay.response.WeiXinResponseResult;
import com.yql.biz.vo.pay.wx.ResponseHandler;
import com.yql.biz.vo.pay.wx.WeiXinNotifyVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
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
    @Transactional
    public WeiXinResponseResult callPayNotify(ResponseHandler responseHandler) {
        log.debug("==============debug==========");
        WeiXinResponseResult weiXinResponse = new WeiXinResponseResult(WxPayResult.SUCCESS);
        responseHandler.setKey(applicationConf.getWxKey());
        SortedMap allParameters = responseHandler.getAllParameters();
        WeiXinNotifyVo weiXinNotifyVo = payOrderParamHelper.getWxCallBackParam(allParameters);
        PayOrderAccount orderAccount = payOrderAccountDao.findByOrderNo(weiXinNotifyVo.getOutTradeNo());
        if (orderAccount!=null){
            //订单已经成功处理,直接返回给微信成功状态
            if (PayStatus.PAY_SUCCESS.getValue().equals(orderAccount.getPayStatus())){
                return  weiXinResponse;
            }else {
                ResultPayOrder resultPayOrder = new ResultPayOrder();
                sendMessageHelper.sendWxNotify(resultPayOrder,weiXinNotifyVo.getOutTradeNo());
                if (WxPayResult.SUCCESS.name().equals(weiXinNotifyVo.getResultCode())){
                    boolean tenpaySign = responseHandler.isTenpaySign();
                    log.debug(" 微信异步通知: "+responseHandler.getDebugInfo());
                    if (tenpaySign){
                        if (weiXinNotifyVo.getResultCode().equals(WxPayResult.SUCCESS.name())){
                            orderAccount.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
                            Date date = PayUtil.dataFormat(weiXinNotifyVo.getTimeEnd());
                            orderAccount.setBankTxTime(date);
                            orderAccount.setTxCode(weiXinNotifyVo.getOpenid());
                            payOrderAccountDao.save(orderAccount);
                        }
                        sendMessageHelper.sendWxNotifyResult(weiXinResponse,resultPayOrder,weiXinNotifyVo);
                    }else {
                        weiXinResponse.setReturnCode(WxPayResult.FAIL);
                        weiXinResponse.setReturnMsg("签名失败");
                    }
                }else {
                    weiXinResponse.setReturnCode(WxPayResult.FAIL);
                    weiXinResponse.setReturnMsg(weiXinNotifyVo.getResultCode());
                }
            }
        }else {
            weiXinResponse.setReturnCode(WxPayResult.FAIL);
            weiXinResponse.setReturnMsg("查询不到此商户订单");
        }
        return weiXinResponse;
    }
}
