package com.yql.biz.support.helper;

import com.yql.biz.conf.ApplicationConf;
import com.yql.biz.conf.SecurityConfiguration;
import com.yql.biz.enums.PayType;
import com.yql.biz.exception.MessageRuntimeException;
import com.yql.biz.model.PayBank;
import com.yql.biz.support.OrderNoGenerator;
import com.yql.biz.util.PayUtil;
import com.yql.biz.util.PlatformPayUtil;
import com.yql.biz.vo.PayOrderVo;
import com.yql.biz.vo.pay.Param;
import com.yql.biz.vo.pay.request.Head;
import com.yql.biz.vo.pay.request.PayBody;
import com.yql.biz.vo.pay.request.Request;
import com.yql.biz.vo.pay.wx.WeiXinOrderVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.xml.bind.annotation.XmlElement;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  <p>银行卡快捷支付 封装参数helper</p>
 * @auther simple
 * data 2016/11/21 0021.
 */
@Component
public class PayOrderCardParamHelper implements IPayOrderCardParamHelper{
    private static final Logger log = LoggerFactory.getLogger(PayOrderCardParamHelper.class);
    @Resource
    private ApplicationConf applicationConf;
    @Resource
    private OrderNoGenerator orderNoGenerator;


    @Override
    public Param getPayParam(PayOrderVo payOrderVo,PayBank payBank) {
        int amount = PayUtil.multiply(payOrderVo.getTotalPrice());
        long payNo = orderNoGenerator.generate(payOrderVo.getPayType());
        payOrderVo.setPayNo(payNo);
        Request<PayBody> request = new Request<>();
        Head head = new Head() ;
        head.setInstitutionID(applicationConf.getInstitutionId());
        head.setTxCode(payOrderVo.getTxCode());
        request.setHead(head);
        PayBody payBody = new PayBody();
        payBody.setPaymentNo(Long.toString(payNo)+PayUtil.randomCodeNum(6));
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
    public String getWxPayParam(WeiXinOrderVo weiXinOrderVo) {
        try {
            String wxKey = applicationConf.getWxKey();
            Map<String, Object> param = PlatformPayUtil.obtObjParm(weiXinOrderVo);
            String str = PlatformPayUtil.concatStr(param);
            StringBuffer stringBuffer = new StringBuffer(str);
            stringBuffer.append("&key=").append(wxKey);
            log.debug("wx pay http://.../pay/unifiedorder->:"+stringBuffer.toString());
            String md5Key = PayUtil.md5Key(stringBuffer.toString());
            weiXinOrderVo.setSign(md5Key);
            return PlatformPayUtil.payRequestXml(weiXinOrderVo);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
