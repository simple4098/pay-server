package com.yql.biz.support.pay;

import com.yql.biz.client.IWxPayClient;
import com.yql.biz.conf.ApplicationConf;
import com.yql.biz.enums.PayType;
import com.yql.biz.support.helper.IPayOrderCardParamHelper;
import com.yql.biz.vo.AccountVo;
import com.yql.biz.vo.PayOrderVo;
import com.yql.biz.vo.pay.wx.WeiXinOrderVo;
import com.yql.biz.web.ResponseModel;

import javax.annotation.Resource;

/**
 * <p> 微信支付 </p>
 *
 * @auther simple
 * data 2016/11/25 0025.
 */
public class PayOrderWxPayCreator implements IPayOrderCreator {
    @Resource
    private IPayOrderCardParamHelper payOrderCardParamHelper;
    @Resource
    private ApplicationConf applicationConf;
    @Resource
    private IWxPayClient wxPayClient;

    @Override
    public PayOrderVo transform(PayOrderVo payOrderVo) {
        WeiXinOrderVo weiXinOrderVo = new WeiXinOrderVo();
        weiXinOrderVo.setNotifyUrl(applicationConf.getWxNotifyUrl());
        weiXinOrderVo.setOutTradeNo(payOrderVo.getOrderNo());
        weiXinOrderVo.setTotalFee(payOrderVo.totalFee());
        weiXinOrderVo.setSpbillCreateIp(payOrderVo.getSpbillCreateIp());
        weiXinOrderVo.setBody("零享购-消费");
        weiXinOrderVo.setAppId(applicationConf.getAppid());
        weiXinOrderVo.setMchId(applicationConf.getMchid());
        String wxPayParam = payOrderCardParamHelper.getWxPayParam(weiXinOrderVo);
        ResponseModel<AccountVo> pay = wxPayClient.pay(wxPayParam);
        return null;
    }

    @Override
    public boolean supports(PayOrderVo payOrderVo) {
        return PayType.WX_PAY.equals(payOrderVo.getPayType());
    }
}
