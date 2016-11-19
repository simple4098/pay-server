package com.yql.biz.vo;

import com.yql.biz.enums.RealNameAuthType;

/**
 * Created by wangdayin on 2016/11/01 17:26.
 */
public class UserBasicInfoVo {
    private int id;
    //用户code
    private String userCode;

    private String userName;

    //邮件地址
    private String email;

    //用户性别,1:男；0：女
    private int gender;

    //省份名称
    private String provinceName;

    //省份code
    private String provinceCode;

    //城市名称
    private String cityName;

    //城市code
    private String cityCode;

    //区域名称
    private String areaName;

    //区域code
    private String areaCode;

    //详细地址
    private String address;

    //是否实名认证:1:认证；0：未认证
    private int weatherAuth;

    //实名认证类型
    private RealNameAuthType authType;

    //头像地址
    private String imgUrl;

    //身份证照片地址
    private String idCardUrl;

    //身份证号码
    private String idCard;

    //真实姓名
    private String realName;
    //用户类型
    private String userType;
    //会员类型
    private String memberType;
    //所辖区域
    private String precinctCode;

    public String getPrecinctCode() {
        return precinctCode;
    }

    public void setPrecinctCode(String precinctCode) {
        this.precinctCode = precinctCode;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserBasicInfoVo(String userCode, String userName, String email, int gender, String provinceName, String provinceCode, String cityName, String cityCode, String areaName, String areaCode, String address, int weatherAuth, RealNameAuthType authType, String imgUrl, String idCardUrl, String idCard, String realName) {
        this.userCode = userCode;
        this.userName = userName;
        this.email = email;
        this.gender = gender;
        this.provinceName = provinceName;
        this.provinceCode = provinceCode;
        this.cityName = cityName;
        this.cityCode = cityCode;
        this.areaName = areaName;
        this.areaCode = areaCode;
        this.address = address;
        this.weatherAuth = weatherAuth;
        this.authType = authType;
        this.imgUrl = imgUrl;
        this.idCardUrl = idCardUrl;
        this.idCard = idCard;
        this.realName = realName;
    }

    public UserBasicInfoVo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getWeatherAuth() {
        return weatherAuth;
    }

    public void setWeatherAuth(int weatherAuth) {
        this.weatherAuth = weatherAuth;
    }

    public RealNameAuthType getAuthType() {
        return authType;
    }

    public void setAuthType(RealNameAuthType authType) {
        this.authType = authType;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getIdCardUrl() {
        return idCardUrl;
    }

    public void setIdCardUrl(String idCardUrl) {
        this.idCardUrl = idCardUrl;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }



}


