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

    /**
     * 查询用户的密保集合
     * @param payAccountId  支付id
     */
    List<SecurityProblem> findByPayAccountId(int payAccountId);

    /**
     * 根据payAccountId 、problemId查询密保问题
     * @param payAccountId 账户id
     * @param problemId 问题id
     */
    SecurityProblem findByPayAccountIdAndProblemId(int payAccountId, Integer problemId);
}
