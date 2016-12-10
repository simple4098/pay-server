package com.yql.biz.vo;

import com.yql.biz.model.FyBankCityCode;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * <p> 富友城市编码 </p>
 * @auther simple
 * data 2016/12/8 0008.
 */
public class FyBankCityCodeVo{
    private String cityId;
    private String cityName;
    private String provinceId;
    private String provinceName;
    private List<FyBankCityCodeVo> fyBankCityCodeVoList;

    public List<FyBankCityCodeVo> getFyBankCityCodeVoList() {
        return fyBankCityCodeVoList;
    }

    public void setFyBankCityCodeVoList(List<FyBankCityCodeVo> fyBankCityCodeVoList) {
        this.fyBankCityCodeVoList = fyBankCityCodeVoList;
    }

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

    public static FyBankCityCodeVo toVo(FyBankCityCode fyBankCityCode) {
        FyBankCityCodeVo fyBankCityCodeVo = new FyBankCityCodeVo();
        BeanUtils.copyProperties(fyBankCityCode,fyBankCityCodeVo);
        return fyBankCityCodeVo;
    }
}
