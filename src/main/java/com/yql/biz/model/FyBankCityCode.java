package com.yql.biz.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <p> 富友城市编码 </p>
 * @auther simple
 * data 2016/12/8 0008.
 */
@Entity
@Table(name = "fy_bank_city_code")
public class FyBankCityCode extends Domain{
    private String cityId;
    private String cityName;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
