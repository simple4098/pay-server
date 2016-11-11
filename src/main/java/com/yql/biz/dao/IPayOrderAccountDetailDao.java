package com.yql.biz.dao;

import com.yql.biz.model.PayOrderAccountDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * creator simple
 * Created by Administrator
 * data 2016/11/11 0011.
 */
@Repository
public interface IPayOrderAccountDetailDao extends JpaRepository<PayOrderAccountDetail,Integer> {
}
