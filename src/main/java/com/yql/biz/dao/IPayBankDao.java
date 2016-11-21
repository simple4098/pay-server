package com.yql.biz.dao;

import com.yql.biz.model.PayBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>支付银行卡dao</p>
 * creator simple
 * data 2016/11/7 0007.
 */
@Repository
public interface IPayBankDao extends JpaRepository<PayBank,Integer>{
    /**
     * 根据accountId查询银行卡列表信息
     * @param payAccountId  accountId
     */
    List<PayBank> findByPayAccountIdOrderBySort(int payAccountId);

    /**
     * 根据acountId 和 bankCard查询支付银行卡的信息
     * @param payAccountId accountId
     * @param bankCard
     * @return
     */
    PayBank findByPayAccountIdAndBankCard(int payAccountId, String bankCard);

    /**
     * 根据用户code查询是否删除的银行卡列表
     * @param userCode 用户code
     * @param deleted 是否删除
     */
    List<PayBank> findByUserCodeAndDeleted(String userCode,boolean deleted);

    /**
     * 查询此用户绑定银行卡信息
     * @param txCode 交易编码
     * @param payAccountId 支付accountId
     */
    PayBank findByPayAccountIdAndTxCode( Integer payAccountId,String txCode);

    /**
     * 根据userCode txCode 查询绑定的银行卡信息
     * @param userCode  用户编码
     * @param txCode 交易码
     */
    PayBank findByUserCodeAndTxCode(String userCode, String txCode);
}
