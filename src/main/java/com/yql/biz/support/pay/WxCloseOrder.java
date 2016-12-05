package com.yql.biz.support.pay;

import com.yql.biz.client.IWxPayClient;
import com.yql.biz.conf.ApplicationConf;
import com.yql.biz.enums.PayType;
import com.yql.biz.model.PayOrderAccount;
import com.yql.biz.support.helper.IPayOrderParamHelper;
import com.yql.biz.util.PlatformPayUtil;
import com.yql.biz.vo.pay.response.WeiXinCloseOrderResponse;
import com.yql.biz.vo.pay.wx.ResponseHandler;
import com.yql.biz.vo.pay.wx.WxOrderQueryVo;
import com.yql.biz.web.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p> 微信关闭订单实现类 </p>
 * @auther simple
 * data 2016/12/3 0003.
 */
@Component
public class WxCloseOrder implements  ICloseOrder {
    private static  final Logger log = LoggerFactory.getLogger(WxCloseOrder.class);
    @Resource
    private IPayOrderParamHelper payOrderCardParamHelper;
    @Resource
    private IWxPayClient wxPayClient;
    @Resource
    private ApplicationConf applicationConf;

    @Override
    public WeiXinCloseOrderResponse closeOrder(PayOrderAccount payOrderVo) {
        WxOrderQueryVo wxOrderQueryVo = new WxOrderQueryVo();
        wxOrderQueryVo.setOutTradeNo(payOrderVo.getPayNo());
        wxOrderQueryVo.setAppId(applicationConf.getAppid());
        wxOrderQueryVo.setMchId(applicationConf.getMchid());
        String sign = payOrderCardParamHelper.getSign(wxOrderQueryVo);
        wxOrderQueryVo.setSign(sign);
        String payRequestXml = null;
        try {
            payRequestXml = PlatformPayUtil.payRequestXml(wxOrderQueryVo);
        } catch (Exception e) {
            log.error("解析xml异常:",e);
        }
        ResponseModel<WeiXinCloseOrderResponse> closeOrder = wxPayClient.closeOrder(payRequestXml);
        WeiXinCloseOrderResponse data = closeOrder.getData();
        return data;
    }

    @Override
    public boolean supports(PayOrderAccount payOrderVo) {
        return PayType.WX_PAY.equals(payOrderVo.getPayType());
    }
}
