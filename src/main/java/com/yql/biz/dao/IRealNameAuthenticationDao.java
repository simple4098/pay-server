package com.yql.biz.dao;

import com.yql.biz.model.RealNameAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>实名认证dao</p>
 * creator simple
 * data 2016/11/7 0007.
 */
@Repository
public interface IRealNameAuthenticationDao extends JpaRepository<RealNameAuthentication,Integer> {
}
