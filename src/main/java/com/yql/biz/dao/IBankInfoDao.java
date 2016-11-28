package com.yql.biz.dao;

import com.yql.biz.model.BankInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p> 银行支付 </p>
 * @auther simple
 * data 2016/11/18 0018.
 */
@Repository
@CacheConfig(cacheNames = "bank_info")
public interface IBankInfoDao extends JpaRepository<BankInfo,Integer> {
    /**
     * 根据名称查询 银行卡信息
     * @param bankName 银行卡名称
     * @return
     */
    BankInfo findByBankName(String bankName);
}
