package com.yql.biz.support.helper;

import com.yql.biz.model.PayBank;
import com.yql.biz.vo.PayOrderVo;
import com.yql.biz.vo.pay.Param;
import com.yql.biz.vo.pay.wx.WeiXinOrderVo;

/**
 * <p>银行卡快捷支付 封装参数helper</p>
 * creator simple
 * data 2016/11/11 0011.
 */

public interface IPayOrderCardParamHelper {

    Param getPayParam(PayOrderVo payOrderVo,PayBank payBank);

    /**
     * 微信支付参数xml获取，1，转化成key=value（参数名按照）2，拼接API密钥 3 最终转化成xml
     * @param weiXinOrderVo
     * @return
     */
    String getWxPayParam(WeiXinOrderVo weiXinOrderVo);


}
