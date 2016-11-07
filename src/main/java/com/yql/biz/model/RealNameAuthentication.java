package com.yql.biz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * <p>支付实名认证类</p>
 * @author  simple
 * @version 1.0.0
 * data 2016/11/7 0007.
 */
@Entity
@Table(name = "real_name_authentication")
public class RealNameAuthentication extends Domain implements Serializable {
    //支付账户id
    @Column(name = "pay_account_id")
    private Long payAccountId;
    //真实姓名
    @Column(name = "real_name")
    private String realName;
    //身份证号码
    @Column(name = "id_card")
    private String idCard;
    //身份证图片地址
    @Column(name = "id_card_url")
    private String idCardUrl;

    public Long getPayAccountId() {
        return payAccountId;
    }

    public void setPayAccountId(Long payAccountId) {
        this.payAccountId = payAccountId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdCardUrl() {
        return idCardUrl;
    }

    public void setIdCardUrl(String idCardUrl) {
        this.idCardUrl = idCardUrl;
    }
}
