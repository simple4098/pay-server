package com.yql.biz.service;

import com.yql.biz.model.FyBankCityCode;

import java.util.List;

/**
 * <p> 描述 </p>
 * @auther simple
 * data 2016/12/8 0008.
 */
public interface IFyBankCityCodeService {

    /**
     * 查询所有的城市编码
     */
    List<FyBankCityCode> findAllCityCode();
}
