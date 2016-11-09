package com.yql.biz.dao;

import com.yql.biz.model.SecurityProblem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>密保问题Dao</p>
 * creator simple
 * data 2016/11/7 0007.
 */
@Repository
public interface ISecurityProblemDao extends JpaRepository<SecurityProblem,Integer>{
    List<SecurityProblem> findByPayAccountId(int id);
}
