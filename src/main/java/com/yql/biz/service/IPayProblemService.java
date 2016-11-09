package com.yql.biz.service;

import com.yql.biz.vo.PayProblemDto;
import com.yql.biz.model.SecurityProblem;
import com.yql.biz.vo.SecurityProblemVo;

import java.util.List;

/**
 * creator simple
 * Created by Administrator
 * data 2016/11/8 0008.
 */
public interface IPayProblemService {

    /**
     * 查询没有删除问题集合
     */
    List<PayProblemDto> findPayProblemList();

    /**
     * 保存用户密保问题
     * @param json json 密保问题
     * @return
     */
    List<SecurityProblem> saveySecurity(String json);

    /**
     * 查询用户的密保问题
     * @param userCode 用户code
     */
    List<SecurityProblemVo> findAccountSecurity(String userCode);
}
