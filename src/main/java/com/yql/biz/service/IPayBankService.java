package com.yql.biz.service;

import com.yql.biz.model.PayBank;
import com.yql.biz.vo.PayBankVo;

import java.util.List;

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
    PayBank savePayBank(PayBankVo payBankVo);

    /**
     * 根据用户code查询没有删除的银行卡列表
     * @param userCode 用户code
     * @return 银行卡列表
     */
    List<PayBankVo> findByUserCode(String userCode);

    /**
     * 删除绑定银行卡
     * @param txCode 交易编码
     * @param payAccountId 支付accountId
     */
    void delBangBank(String txCode,Integer payAccountId);
}
