package com.yql.biz.service;

import com.yql.biz.model.PayOrderAccount;
import com.yql.biz.vo.PayOrderVo;

/**
 * <p>支付账单</p>
 * creator simple
 * data 2016/11/10 0010.
 */
public interface IPayOrderAccountService {

    PayOrderVo order(PayOrderVo payOrderVo);

}
