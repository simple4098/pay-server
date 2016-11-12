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
     * 根据用户code查询，银行卡列表
     * @param userCode 用户code
     */
    List<PayBank> findByUserCode(String userCode);
}
