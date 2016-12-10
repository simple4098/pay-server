package com.yql.biz.service.impl;

import com.alibaba.fastjson.JSON;
import com.yql.biz.client.IFyPayForClient;
import com.yql.biz.client.IWxPayClient;
import com.yql.biz.conf.ApplicationConf;
import com.yql.biz.dao.IPayBankDao;
import com.yql.biz.dao.IPayOrderAccountDao;
import com.yql.biz.dao.IPayOrderAccountDetailDao;
import com.yql.biz.enums.PayType;
import com.yql.biz.enums.fy.FyRequestType;
import com.yql.biz.enums.pay.PayStatus;
import com.yql.biz.enums.pay.WxPayResult;
import com.yql.biz.enums.pay.WxPayType;
import com.yql.biz.exception.MessageRuntimeException;
import com.yql.biz.model.PayAccount;
import com.yql.biz.model.PayBank;
import com.yql.biz.model.PayOrderAccount;
import com.yql.biz.model.PayOrderAccountDetail;
import com.yql.biz.service.IPayOrderAccountService;
import com.yql.biz.support.constants.PayConstants;
import com.yql.biz.support.helper.IPayAccountServiceHelper;
import com.yql.biz.support.helper.IPayOrderParamHelper;
import com.yql.biz.support.helper.SendMessageHelper;
import com.yql.biz.support.pay.CloseOrderComposition;
import com.yql.biz.support.pay.PayOrderCreatorComposition;
import com.yql.biz.support.pay.QueryOrderComposition;
import com.yql.biz.util.PayDateUtil;
import com.yql.biz.util.PayUtil;
import com.yql.biz.util.PlatformPayUtil;
import com.yql.biz.vo.*;
import com.yql.biz.vo.pay.fy.FyPayForRequest;
import com.yql.biz.vo.pay.fy.FyPayForResponse;
import com.yql.biz.vo.pay.fy.FyPayRequest;
import com.yql.biz.vo.pay.response.WeiXinCloseOrderResponse;
import com.yql.biz.vo.pay.response.WeiXinResponse;
import com.yql.biz.vo.pay.response.WeiXinResponseResult;
import com.yql.biz.vo.pay.wx.ResponseHandler;
import com.yql.biz.vo.pay.wx.WeiXinAppRequest;
import com.yql.biz.vo.pay.wx.WeiXinNotifyVo;
import com.yql.biz.vo.pay.wx.WeiXinOrderVo;
import com.yql.biz.web.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

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
    @Resource
    private IPayBankDao payBankDao;
    @Resource(name = "queryOrderComposition")
    private QueryOrderComposition queryOrderComposition;
    @Resource(name = "closeOrderComposition")
    private CloseOrderComposition closeOrderComposition;
    @Resource
    private IPayOrderParamHelper payOrderCardParamHelper;
    @Resource
    private IWxPayClient wxPayClient;
    @Resource
    private IFyPayForClient fyPayForClient;

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
        log.debug("支付下单返回data:"+JSON.toJSONString(payOrder));
        return payOrder;
    }


    @Override
    @Transactional
    public WeiXinResponseResult callPayNotify(ResponseHandler responseHandler) {
        log.debug("==============callPayNotify==========");
        WeiXinResponseResult weiXinResponse = new WeiXinResponseResult(WxPayResult.SUCCESS);
        responseHandler.setKey(applicationConf.getWxKey());
        SortedMap allParameters = responseHandler.getAllParameters();
        WeiXinNotifyVo weiXinNotifyVo = payOrderParamHelper.getWxCallBackParam(allParameters);
        PayOrderAccount orderAccount = payOrderAccountDao.findByPayNo(weiXinNotifyVo.getOutTradeNo());
        if (orderAccount!=null){
            //订单已经成功处理,直接返回给微信成功状态
            if (PayStatus.PAY_SUCCESS.getValue().equals(orderAccount.getPayStatus())){
                return  weiXinResponse;
            }else {
                ResultPayOrder resultPayOrder = new ResultPayOrder();
                //sendMessageHelper.sendWxNotify(resultPayOrder,orderAccount.getOrderNo());
                if (WxPayResult.SUCCESS.name().equals(weiXinNotifyVo.getResultCode())){
                    boolean tenpaySign = responseHandler.isTenpaySign();
                    log.debug(" 微信异步通知: "+responseHandler.getDebugInfo());
                    if (tenpaySign){
                        if (weiXinNotifyVo.getResultCode().equals(WxPayResult.SUCCESS.name())){
                            orderAccount.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
                            Date date = PayUtil.dataFormat(weiXinNotifyVo.getTimeEnd());
                            orderAccount.setBankTxTime(date);
                            orderAccount.setTxCode(weiXinNotifyVo.getOpenid());
                            orderAccount.setBankCode(weiXinNotifyVo.getBankType());
                            orderAccount.setPayOrder(weiXinNotifyVo.getTransactionId());
                            payOrderAccountDao.save(orderAccount);
                        }
                        weiXinNotifyVo.setOutTradeNo(orderAccount.getOrderNo());
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

    @Override
    @Transactional
    public void updateDrawMoneyStatus(String payOrderNo,Integer payStatus) {
        PayOrderAccount byOrderNo = payOrderAccountDao.findByOrderNo(payOrderNo);
        if (!PayType.DRAW_MONEY.equals(byOrderNo.getPayType())) throw new MessageRuntimeException("error.payserver.payType.param");
        byOrderNo.setPayStatus(payStatus);
        payOrderAccountDao.save(byOrderNo);
        PayOrderVo payOrderVo = new PayOrderVo();
        payOrderVo.setOrderNo(payOrderNo);
        payOrderVo.setPayStatus(payStatus);
        sendMessageHelper.sendDrawMoney(payOrderVo);
    }

    @Transactional(readOnly = true)
    @Override
    public List<DrawMoneyVo> findDrawMoneyList() {
        Date startTime = PayDateUtil.getStartTime();
        Date endTime = PayDateUtil.getEndTime();
        List<PayOrderAccount> list = payOrderAccountDao.findByPayTypeAndPayStatusAndCreatedTimeBetween(PayType.DRAW_MONEY,PayStatus.HANDLING.getValue(),startTime,endTime);
        List<DrawMoneyVo> drawMoneyVos = new ArrayList<>();
        DrawMoneyVo drawMoneyVo = null;
        PayBank bank = null;
        for (PayOrderAccount order: list) {
            bank = payBankDao.findByTxCode(order.getTxCode());
            drawMoneyVo = DrawMoneyVo.toVo(order,bank);
            drawMoneyVos.add(drawMoneyVo);
        }
        return drawMoneyVos;
    }

    @Override
    public void updateDrawMoney() {
        Date startTime = PayDateUtil.getStartTime();
        Date endTime = PayDateUtil.getEndTime();
        List<PayOrderAccount> list = payOrderAccountDao.findByPayTypeAndPayStatusAndCreatedTimeBetween(PayType.DRAW_MONEY,PayStatus.HANDLING.getValue(),startTime,endTime);
        FyPayForRequest fyPayForRequest = null;
        for (PayOrderAccount order: list) {
            PayBank p = payBankDao.findByTxCode(order.getTxCode());
            int cent = PayUtil.priceToCent(order.getTotalPrice());
            fyPayForRequest = new FyPayForRequest(p.getBankId(),p.getCityNo(),p.getBankCard(),p.getCardholder(),cent,p.getPhoneNumber());
            String payRequestXml = PlatformPayUtil.payRequestXml(fyPayForRequest);
            FyPayRequest fyPayRequest = new FyPayRequest(applicationConf.getFyMerid(), FyRequestType.payforreq,payRequestXml);
            String md5String = fyPayRequest.toMd5String(applicationConf.getFyKey());
            fyPayRequest.setMac(md5String);
            FyPayForResponse fyPayForResponse = fyPayForClient.payFor(fyPayRequest);
            int payStatus = PayStatus.PAY_UNSUCCESS.getValue();
            if (PayConstants.FY_PAY_FOR_SUCCESS.equals(fyPayForResponse.getRet())){
                payStatus = PayStatus.PAY_SUCCESS.getValue();
            }
            updateDrawMoneyStatus(order.getOrderNo(),payStatus);
        }
    }

    @Override
    public ResultWxQueryOrder findWxOrderInfo(String orderNo) {
        PayOrderAccount orderAccount = payOrderAccountDao.findByOrderNo(orderNo);
        ResultWxQueryOrder wxQueryOrder = queryOrderComposition.transform(orderAccount);
        return wxQueryOrder;
    }

    @Override
    public WeiXinCloseOrderResponse closeOrder(String orderNo) {
        PayOrderAccount payOrderAccount = payOrderAccountDao.findByOrderNo(orderNo);
        WeiXinCloseOrderResponse transform = closeOrderComposition.transform(payOrderAccount);
        return transform;
    }

    @Override
    public AppPrepayInfo prepay(String orderNo,String spbillCreateIp) {
        PayOrderAccount payOrderAccount = payOrderAccountDao.findByOrderNo(orderNo);
        if (payOrderAccount==null) throw  new MessageRuntimeException("error.payserver.param.order.notnull");
        int priceToCent = PayUtil.priceToCent(payOrderAccount.getTotalPrice());
        WeiXinOrderVo weiXinOrderVo = new WeiXinOrderVo();
        weiXinOrderVo.setNotifyUrl(applicationConf.getWxNotifyUrl());
        weiXinOrderVo.setTotalFee(priceToCent);
        weiXinOrderVo.setSpbillCreateIp(spbillCreateIp);
        weiXinOrderVo.setBody(applicationConf.getWxBody());
        weiXinOrderVo.setAppId(applicationConf.getAppid());
        weiXinOrderVo.setMchId(applicationConf.getMchid());
        weiXinOrderVo.setTradeType(WxPayType.APP);
        weiXinOrderVo.setOutTradeNo(payOrderAccount.getPayNo());
        PayOrderVo payOrderVo = new PayOrderVo();
        payOrderVo.setPayType(PayType.WX_PAY);
        String wxPayParam = payOrderCardParamHelper.getWxPayParam(payOrderVo,weiXinOrderVo);
        ResponseModel<WeiXinResponse> weiXinResponseResponseModel = wxPayClient.sendPrepay(wxPayParam);
        if (weiXinResponseResponseModel!=null && weiXinResponseResponseModel.getData()!=null){
            WeiXinAppRequest weiXinAppRequest = new WeiXinAppRequest();
            WeiXinResponse data = weiXinResponseResponseModel.getData();
            if (data.getReturnCode().equals(WxPayResult.SUCCESS)){
                if (data.getAppId().equals(weiXinOrderVo.getAppId()) && data.getMchId().equals(weiXinOrderVo.getMchId()) ){
                    if (data.getResultCode().equals(WxPayResult.FAIL)) throw new RuntimeException(data.getErrCodeDes());
                    weiXinAppRequest.setAppId(weiXinOrderVo.getAppId());
                    weiXinAppRequest.setPrepayid(data.getPrepayId());
                    weiXinAppRequest.setPartnerid(data.getMchId());
                    weiXinAppRequest.setNonceStr(weiXinOrderVo.getNonceStr());
                    String sign = payOrderCardParamHelper.getSign(weiXinAppRequest);
                    weiXinAppRequest.setSign(sign);
                    log.debug("生成app参数   :"+JSON.toJSONString(weiXinAppRequest));
                    Map<String, Object> stringObjectMap = PlatformPayUtil.obtObjParm(weiXinAppRequest);
                    String json =  JSON.toJSONString(stringObjectMap);
                    return new AppPrepayInfo(json);
                }else {
                    log.error("预付订单异常  : 微信response appID MchId"+data.getAppId()+"\t"+ data.getMchId()+"\n"+" 系统微信账户信息: appID MchId"+weiXinOrderVo.getMchId() +"\t"+weiXinOrderVo.getAppId());
                    throw new RuntimeException("下微信预付订单异常,appID MchId 不一致");
                }
            }else {
                throw new RuntimeException(data.getReturnMsg());
            }
        }
        return null;
    }

    @Override
    public ResultPayOrder findOrderInfo(String orderNo) {
        PayOrderAccount byOrderNo = payOrderAccountDao.findByOrderNo(orderNo);
        PayOrderVo payOrderVo = PayOrderVo.domainToVo(byOrderNo);
        ResultPayOrder payOrder = PayOrderVo.toResultOrder(payOrderVo);
        return payOrder;
    }
}
