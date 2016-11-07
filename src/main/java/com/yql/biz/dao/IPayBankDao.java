package com.yql.biz.dao;

import com.yql.biz.model.PayBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * creator simple
 * Created by Administrator
 * data 2016/11/7 0007.
 */
@Repository
public interface IPayBankDao extends JpaRepository<PayBank,Integer>{
}
