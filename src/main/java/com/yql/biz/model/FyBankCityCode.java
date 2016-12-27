package com.yql.biz.model;

import com.yql.core.model.Domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <p> 富友城市编码 </p>
 * @auther simple
 * data 2016/12/8 0008.
 */
@Entity
@Table(name = "fy_bank_city_code")
public class FyBankCityCode extends Domain {
    private String cityId;
    private String cityName;
    private String provinceId;
    private String provinceName;

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

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}
