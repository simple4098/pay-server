package com.yql.biz.service;

import com.yql.biz.dto.PayProblemDto;
import com.yql.biz.model.PayProblem;

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
}
