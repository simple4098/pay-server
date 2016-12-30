package com.yql.biz.support.helper;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipayHashMap;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.internal.util.RequestParametersHolder;
import com.yql.biz.conf.ApplicationConf;
import com.yql.biz.enums.pay.AliMethod;
import com.yql.biz.enums.pay.WxPayType;
import com.yql.biz.model.PayBank;
import com.yql.biz.model.PayOrderAccount;
import com.yql.biz.support.OrderNoGenerator;
import com.yql.biz.support.constants.PayConstants;
import com.yql.biz.util.PayUtil;
import com.yql.biz.util.PlatformPayUtil;
import com.yql.biz.vo.PayOrderVo;
import com.yql.biz.vo.pay.Param;
import com.yql.biz.vo.pay.ali.AliPayBaseRequest;
import com.yql.biz.vo.pay.ali.AliPayBizRequest;
import com.yql.biz.vo.pay.request.DjPay;
import com.yql.biz.vo.pay.request.Head;
import com.yql.biz.vo.pay.request.PayBody;
import com.yql.biz.vo.pay.request.Request;
import com.yql.biz.vo.pay.wx.WeiXinNotifyVo;
import com.yql.biz.vo.pay.wx.WeiXinOrderVo;
import com.yql.core.exception.MessageRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.xml.bind.annotation.XmlElement;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.SortedMap;

/**
 *  <p>银行卡快捷支付 封装参数helper</p>
 * @auther simple
 * data 2016/11/21 0021.
 */
@Component
public class PayOrderParamHelper implements IPayOrderParamHelper {
    private static final Logger log = LoggerFactory.getLogger(PayOrderParamHelper.class);
    @Resource
    private ApplicationConf applicationConf;
    @Resource
    private OrderNoGenerator orderNoGenerator;


    @Override
    public Param getPayParam(PayOrderVo payOrderVo,PayBank payBank) {
        int amount = PayUtil.priceToCent(payOrderVo.getTotalPrice());
        String payNo = orderNoGenerator.generate(payOrderVo.getPayType());
        payOrderVo.setPayNo(payNo);
        Request<PayBody> request = new Request<>();
        Head head = new Head() ;
        head.setInstitutionID(applicationConf.getInstitutionId());
        head.setTxCode(payOrderVo.getTxCode());
        request.setHead(head);
        PayBody payBody = new PayBody();
        payBody.setPaymentNo(payNo);
        payBody.setTxSNBinding(payBank.getTxSNBinding());
        payBody.setSettlementFlag(payBank.getSettlementFlag());
        payBody.setAmount(amount);
        payBody.setRemark(payOrderVo.getRemark());
        request.setBody(payBody);
        try {
            return PlatformPayUtil.payRequest(request);
        } catch (Exception e) {
            log.error("银行卡快捷支付 封装参数helper",e);
            throw  new MessageRuntimeException("error.payserver.payServer.DJ");
        }
    }

    @Override
    public String getWxPayParam(WeiXinOrderVo weiXinOrderVo, PayOrderAccount payOrderAccount) {
        int priceToCent = PayUtil.priceToCent(payOrderAccount.getTotalPrice());
        weiXinOrderVo.setNotifyUrl(applicationConf.getWxNotifyUrl());
        weiXinOrderVo.setTotalFee(priceToCent);
        weiXinOrderVo.setBody(applicationConf.getWxBody());
        weiXinOrderVo.setAppId(applicationConf.getAppid());
        weiXinOrderVo.setMchId(applicationConf.getMchid());
        weiXinOrderVo.setTradeType(WxPayType.APP);
        weiXinOrderVo.setOutTradeNo(payOrderAccount.getPayNo());
        try {
            String sign = getSign(weiXinOrderVo);
            weiXinOrderVo.setSign(sign);
            String payRequestXml = PlatformPayUtil.payRequestXml(weiXinOrderVo);
            log.debug("微信统一下单,最终的提交xml:\n"+payRequestXml);
            return payRequestXml;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public WeiXinNotifyVo getWxCallBackParam(SortedMap allParameters) {
        Method[] methods = WeiXinNotifyVo.class.getMethods();
        WeiXinNotifyVo weiXinNotifyVo = new WeiXinNotifyVo();
        for (Method m:methods){
            XmlElement annotation = m.getAnnotation(XmlElement.class);
            if (annotation!=null){
                String name = annotation.name();
                Object value = allParameters.get(name);
                try {
                    String getName = m.getName();
                    String fieldName = getName.substring(3);
                    Method method = null;
                    if (value instanceof  String){
                        method =  weiXinNotifyVo.getClass().getMethod("set" + fieldName,String.class);
                    }
                    if (value instanceof Integer){
                        method =  weiXinNotifyVo.getClass().getMethod("set" + fieldName,Integer.class);
                    }
                    if (method!=null){
                        method.invoke(weiXinNotifyVo,value);
                    }
                } catch (Exception e) {
                    log.error("微信支付通知 对象转化异常",e);
                }
            }
        }
        return weiXinNotifyVo;
    }

    @Override
    public String getSign(DjPay djPay) {
        String wxKey = applicationConf.getWxKey();
        Map<String, Object> param = PlatformPayUtil.obtObjParam(djPay);
        String str = PlatformPayUtil.sortParam(param);
        StringBuffer stringBuffer = new StringBuffer(str);
        log.debug("并按照参数名ASCII字典序排序生成字符串:\n"+stringBuffer.toString());
        stringBuffer.append("key=").append(wxKey);
        log.debug("连接商户key:\n"+stringBuffer.toString());
        String md5Key = PayUtil.MD5Encode(stringBuffer.toString(),"UTF-8").toUpperCase();
        log.debug("WeiXin Pay sign:"+md5Key);
        return md5Key;
    }

    @Override
    public String getFyOrderPayParam(PayOrderVo payOrderVo, PayBank payBank) {
       /* String payNo = orderNoGenerator.generate(payOrderVo.getPayType());
        payOrderVo.setPayNo(payNo);
        Integer amount = PayUtil.priceToCent(payOrderVo.getTotalPrice());
        FyCreatedOrderRequest fyCreatedOrderRequest = new FyCreatedOrderRequest("0002900F0096235", amount.toString(),"5old71wihg2tqjug9kkpxnhx9hiujoqj");
        fyCreatedOrderRequest.toMd5();
        return  PlatformPayUtil.payRequestXml(fyCreatedOrderRequest);*/
       return null;
    }

    @Override
    public AliPayBaseRequest getAliAppPayParam(PayOrderAccount payOrderAccount) {
        AliPayBaseRequest aliPayBaseRequest = new AliPayBaseRequest(applicationConf.getAliPayAppId(), AliMethod.ALI_APP_PAY.getMethodName(),applicationConf.getAliNotifyUrl());
        AliPayBizRequest bizRequest = new AliPayBizRequest();
        bizRequest.setTotal_amount(payOrderAccount.getTotalPrice().toString());
        bizRequest.setProduct_code(PayConstants.ALI_APP_PRODUCT_CODE);
        bizRequest.setOut_trade_no(payOrderAccount.getPayNo());
        bizRequest.setSubject(applicationConf.getWxBody());
        aliPayBaseRequest.setBizContent(JSON.toJSONString(bizRequest));
        Map<String, String> paramMap = PlatformPayUtil.obtAliPayObjParam(aliPayBaseRequest);
        AlipayHashMap appParams = new AlipayHashMap(paramMap);
        RequestParametersHolder requestHolder = new RequestParametersHolder();
        requestHolder.setApplicationParams(appParams);
        String signContent = AlipaySignature.getSignatureContent(requestHolder);
        try {
            String rsaSign = AlipaySignature.rsaSign(signContent, applicationConf.getAliPrivateKey(), aliPayBaseRequest.getCharset(), aliPayBaseRequest.getSignType());
            aliPayBaseRequest.setSign(rsaSign);
        } catch (AlipayApiException e) {
            throw new RuntimeException(e.getMessage());
        }

        return aliPayBaseRequest;
    }

}
