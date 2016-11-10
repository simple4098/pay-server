package com.yql.biz.service;

import com.yql.biz.model.PayBank;
import com.yql.biz.vo.PayBankVo;

/**
 * creator simple
 * data 2016/11/10 0010.
 */
public interface IPayBankService {
    /**
     * 保存支付银行卡信息
     * @param payBankVo
     * @return
     */
    PayBank savePayBanke(PayBankVo payBankVo);
}
