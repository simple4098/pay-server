package com.yql.biz.dao;

import com.yql.biz.model.PayAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>支付账户dao</p>
 * creator simple
 * data 2016/11/7 0007.
 */
@Repository
public interface IPayAccountDao  extends JpaRepository<PayAccount, Integer> {

    PayAccount findByUserCode(String userCode);


}
