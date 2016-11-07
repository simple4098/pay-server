package com.yql.biz.dao;

import com.yql.biz.model.PayOrderAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <P>支付记录dao</P>
 * creator simple
 * data 2016/11/7 0007.
 */
@Repository
public interface IPayOrderAccountDao  extends JpaRepository<PayOrderAccount,Integer>{
}