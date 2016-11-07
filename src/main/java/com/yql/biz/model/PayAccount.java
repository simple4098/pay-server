package com.yql.biz.model;

import com.yql.biz.enums.SamllPayMoney;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * <p>支付账号实体类</p>
 * @author  simple
 * @version 1.0.0
 * data 2016/11/7 0007.
 */
@Entity
@Table(name = "pay_account")
public class PayAccount extends  Domain implements Serializable {
    //用户code
    @Column(name = "user_code")
    private String userCode;
    //支付密码
    @Column(name = "pay_password")
    private String payPassword;
    //随机数
    @Column(name = "random_code")
    private int randomCode;
    //是否默认系统支付银行卡顺序 1 默认 0 不默认
    @Column(name = "system_pay_seq")
    private boolean systemPaySeq;
    //小额支付 0 未开通 1 开通
    @Column(name = "small_pay")
    private boolean smallPay;
    //小额支付金额 枚举 MONEY_200  MONEY_500 MONEY_800 MONEY_1000 MONEY_2000
    @Column(name = "samll_pay_money")
    private SamllPayMoney samllPayMoney;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public int getRandomCode() {
        return randomCode;
    }

    public void setRandomCode(int randomCode) {
        this.randomCode = randomCode;
    }

    public boolean isSystemPaySeq() {
        return systemPaySeq;
    }

    public void setSystemPaySeq(boolean systemPaySeq) {
        this.systemPaySeq = systemPaySeq;
    }

    public boolean isSmallPay() {
        return smallPay;
    }

    public void setSmallPay(boolean smallPay) {
        this.smallPay = smallPay;
    }

    public SamllPayMoney getSamllPayMoney() {
        return samllPayMoney;
    }

    public void setSamllPayMoney(SamllPayMoney samllPayMoney) {
        this.samllPayMoney = samllPayMoney;
    }
}
