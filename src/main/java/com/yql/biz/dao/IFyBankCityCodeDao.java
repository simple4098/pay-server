package com.yql.biz.dao;

import com.yql.biz.model.FyBankCityCode;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * <p> 查询城市编码dao </p>
 * @auther simple
 * data 2016/12/8 0008.
 */
@Repository
@CacheConfig(cacheNames = "fy_bank_city_code")
public interface IFyBankCityCodeDao  extends JpaRepository<FyBankCityCode,Integer>{

    @Cacheable
    List<FyBankCityCode> findAll();

    @Cacheable
    FyBankCityCode findByCityName(String cityName);
}
