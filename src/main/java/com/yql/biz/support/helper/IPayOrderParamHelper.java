package com.yql.biz.support.helper;

import com.yql.biz.model.PayBank;
import com.yql.biz.model.PayOrderAccount;
import com.yql.biz.vo.PayOrderVo;
import com.yql.biz.vo.pay.Param;
import com.yql.biz.vo.pay.ali.AliPayBaseRequest;
import com.yql.biz.vo.pay.request.DjPay;
import com.yql.biz.vo.pay.wx.WeiXinNotifyVo;
import com.yql.biz.vo.pay.wx.WeiXinOrderVo;

import java.util.SortedMap;

/**
 * <p>银行卡快捷支付 封装参数helper</p>
 * creator simple
 * data 2016/11/11 0011.
 */

public interface IPayOrderParamHelper {

    /**
     * 快捷支付 返回对象
     * @param payOrderVo 支付订单对象
     * @param payBank 快捷银行对象
     * @return
     */
    Param getPayParam(PayOrderVo payOrderVo,PayBank payBank);

    /**
     * 微信支付参数xml获取，1，转化成key=value（参数名按照）2，拼接API密钥 3 最终转化成xml
     * @param weiXinOrderVo
     * @return
     */
    String getWxPayParam( WeiXinOrderVo weiXinOrderVo, PayOrderAccount payOrderAccount);

    /**
     * 根据不同对象生成签名值
     * @param t
     */
    <T extends DjPay>  String   getSign(T t);


    WeiXinNotifyVo getWxCallBackParam(SortedMap allParameters);

    /**
     * 富友下单参数
     * @param payOrderVo 订单信息
     * @param payBank 绑定银行卡信息
     */
    String getFyOrderPayParam(PayOrderVo payOrderVo, PayBank payBank);

    /**
     * ali app pay param
     * @param payOrderAccount 订单信息
     */
    AliPayBaseRequest getAliAppPayParam(PayOrderAccount payOrderAccount);
}
