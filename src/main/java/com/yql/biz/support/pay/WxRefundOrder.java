package com.yql.biz.support.pay;

import com.yql.biz.client.IWxPayClient;
import com.yql.biz.enums.PayType;
import com.yql.biz.enums.pay.WxPayResult;
import com.yql.biz.model.PayOrderAccount;
import com.yql.biz.vo.pay.wx.WxRefundOrderResponse;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p> 订单退款 </p>
 * @auther simple
 * data 2016/12/23 0023.
 */
@Component
public class WxRefundOrder implements IRefundOrder {
    @Resource
    private IWxPayClient wxPayClient;

    @Override
    public boolean refundOrder(PayOrderAccount payOrderAccount) {
      WxRefundOrderResponse refundOrder = wxPayClient.refundOrder(payOrderAccount);
      if (WxPayResult.SUCCESS.name().equals(refundOrder.getReturnCode()) && WxPayResult.SUCCESS.name().equals(refundOrder.getResultCode())){
          return true;
      }
      return false;
    }

    @Override
    public boolean supports(PayOrderAccount payOrderAccount) {
        return PayType.WX_PAY.equals(payOrderAccount.getPayType());
    }
}
