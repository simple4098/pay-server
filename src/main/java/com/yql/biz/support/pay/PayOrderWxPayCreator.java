package com.yql.biz.support.pay;

import com.alibaba.fastjson.JSON;
import com.yql.biz.client.IWxPayClient;
import com.yql.biz.conf.ApplicationConf;
import com.yql.biz.enums.PayType;
import com.yql.biz.enums.pay.WxPayResult;
import com.yql.biz.support.helper.IPayOrderParamHelper;
import com.yql.biz.vo.PayOrderVo;
import com.yql.biz.vo.pay.response.WeiXinResponse;
import com.yql.biz.vo.pay.wx.WeiXinAppRequest;
import com.yql.biz.vo.pay.wx.WeiXinOrderVo;
import com.yql.biz.web.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> 微信支付 </p>
 *
 * @auther simple
 * data 2016/11/25 0025.
 */
@Service
public class PayOrderWxPayCreator implements IPayOrderCreator {
    private static final Logger logger = LoggerFactory.getLogger(PayOrderWxPayCreator.class);

    @Resource
    private IPayOrderParamHelper payOrderCardParamHelper;
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
        String wxPayParam = payOrderCardParamHelper.getWxPayParam(payOrderVo,weiXinOrderVo);
        ResponseModel<WeiXinResponse> weiXinResponseResponseModel = wxPayClient.sendPrepay(wxPayParam);
        if (weiXinResponseResponseModel!=null && weiXinResponseResponseModel.getData()!=null){
            WeiXinAppRequest weiXinAppRequest = new WeiXinAppRequest();
            WeiXinResponse data = weiXinResponseResponseModel.getData();
            if (data.getAppId().equals(weiXinOrderVo.getAppId()) && data.getMchId().equals(weiXinOrderVo.getMchId()) && data.getResultCode().equals(WxPayResult.SUCCESS) && data.getReturnCode().equals(WxPayResult.SUCCESS)){
                weiXinAppRequest.setAppId(weiXinOrderVo.getAppId());
                weiXinAppRequest.setPrepayid(data.getPrepayId());
                weiXinAppRequest.setPartnerid(data.getMchId());
                weiXinAppRequest.setNonceStr(weiXinOrderVo.getNonceStr());
                String sign = payOrderCardParamHelper.getSign(weiXinAppRequest);
                weiXinAppRequest.setSign(sign);
                logger.debug("生成 app 参数:"+JSON.toJSONString(weiXinAppRequest));
                payOrderVo.setPayOrder(data.getPrepayId());
                payOrderVo.setAppRequest(weiXinAppRequest);
            }
        }
        payOrderVo.setPayStatus(40);
        return payOrderVo;
    }

    @Override
    public boolean supports(PayOrderVo payOrderVo) {
        return PayType.WX_PAY.equals(payOrderVo.getPayType());
    }
}
