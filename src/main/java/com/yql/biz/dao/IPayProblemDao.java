package com.yql.biz.dao;

import com.yql.biz.model.PayProblem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>支付密保问题dao</p>
 * creator simple
 * data 2016/11/8 0008.
 */
@Repository
public interface IPayProblemDao extends JpaRepository<PayProblem,Integer> {

}
