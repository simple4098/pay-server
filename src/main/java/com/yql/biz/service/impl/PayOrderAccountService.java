package com.yql.biz.service.impl;

import com.alibaba.fastjson.JSON;
import com.yql.biz.conf.ApplicationConf;
import com.yql.biz.dao.IPayBankDao;
import com.yql.biz.dao.IPayOrderAccountDao;
import com.yql.biz.dao.IPayOrderAccountDetailDao;
import com.yql.biz.enums.PayType;
import com.yql.biz.enums.pay.PayStatus;
import com.yql.biz.enums.pay.WxPayResult;
import com.yql.biz.exception.MessageRuntimeException;
import com.yql.biz.model.PayAccount;
import com.yql.biz.model.PayBank;
import com.yql.biz.model.PayOrderAccount;
import com.yql.biz.model.PayOrderAccountDetail;
import com.yql.biz.service.IPayOrderAccountService;
import com.yql.biz.support.helper.IPayAccountServiceHelper;
import com.yql.biz.support.helper.IPayOrderParamHelper;
import com.yql.biz.support.helper.SendMessageHelper;
import com.yql.biz.support.pay.PayOrderCreatorComposition;
import com.yql.biz.util.PayDateUtil;
import com.yql.biz.util.PayUtil;
import com.yql.biz.vo.DrawMoneyVo;
import com.yql.biz.vo.PayOrderAccountDetailVo;
import com.yql.biz.vo.PayOrderVo;
import com.yql.biz.vo.ResultPayOrder;
import com.yql.biz.vo.pay.response.WeiXinResponseResult;
import com.yql.biz.vo.pay.wx.ResponseHandler;
import com.yql.biz.vo.pay.wx.WeiXinNotifyVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    @Resource
    private IPayBankDao payBankDao;

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
        PayOrderAccount orderAccount = payOrderAccountDao.findByPayNo(weiXinNotifyVo.getOutTradeNo());
        if (orderAccount!=null){
            //订单已经成功处理,直接返回给微信成功状态
            if (PayStatus.PAY_SUCCESS.getValue().equals(orderAccount.getPayStatus())){
                return  weiXinResponse;
            }else {
                ResultPayOrder resultPayOrder = new ResultPayOrder();
                sendMessageHelper.sendWxNotify(resultPayOrder,orderAccount.getOrderNo());
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
        List<PayOrderAccount> list = payOrderAccountDao.findByPayTypeAndPayStatusAndCreatedTimeBetween(PayType.DRAW_MONEY,PayStatus.DRAW_MONEY_HANDLING.getValue(),startTime,endTime);
        List<DrawMoneyVo> drawMoneyVos = new ArrayList<>();
        DrawMoneyVo drawMoneyVo = null;
        for (PayOrderAccount order: list) {
            drawMoneyVo = new DrawMoneyVo();
            PayBank p = payBankDao.findByTxCode(order.getTxCode());
            //BeanUtils.copyProperties(order,drawMoneyVo);
            drawMoneyVo.setOrderNo(order.getOrderNo());
            drawMoneyVo.setCardholder(p.getCardholder());
            drawMoneyVo.setTotalPrice(order.getTotalPrice());
            drawMoneyVo.setBankCard(p.getBankCard());
            drawMoneyVo.setBankName(p.getBankName());
            drawMoneyVos.add(drawMoneyVo);
        }
        return drawMoneyVos;
    }
}
