package com.yql.biz.support.helper;

import com.yql.biz.conf.ApplicationConf;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;

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
            throw  new MessageRuntimeException("error.payserver.payServer.DJ");
        }
    }
}
