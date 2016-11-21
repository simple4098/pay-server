package com.yql.biz.support.helper;

import com.yql.biz.model.PayBank;
import com.yql.biz.vo.PayOrderVo;
import com.yql.biz.vo.pay.Param;

/**
 * <p>银行卡快捷支付 封装参数helper</p>
 * creator simple
 * data 2016/11/11 0011.
 */

public interface IPayOrderCardParamHelper {

    Param getPayParam(PayOrderVo payOrderVo,PayBank payBank);


}
