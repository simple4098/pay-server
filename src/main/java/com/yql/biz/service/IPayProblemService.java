package com.yql.biz.service;

import com.yql.biz.dto.PayProblemDto;
import com.yql.biz.model.PayProblem;
import com.yql.biz.model.SecurityProblem;

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
}
