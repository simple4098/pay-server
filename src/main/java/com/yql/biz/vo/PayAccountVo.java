package com.yql.biz.vo;

import com.yql.biz.enums.SamllPayMoney;
import com.yql.biz.model.PayAccount;
import com.yql.biz.util.PayUtil;

import javax.validation.constraints.NotNull;

/**
 * <p>支付账号vo对象</p>
 * creator simple
 * data 2016/11/10 0010.
 */
public class PayAccountVo {
    private Integer id;
    @NotNull(message = "{com.yql.validation.constraints.userCode.message}")
    private String userCode;
    //支付密码
    private String payPassword;
    //旧的支付密码
    private String oldPayPassword;
    //随机数
    private String randomCode  = PayUtil.randomCode(6);
    //是否默认系统支付银行卡顺序 1 默认 0 不默认
    private boolean systemPaySeq;
    //小额支付 0 未开通 1 开通
    private boolean smallPay;
    //小额支付金额 枚举 MONEY_200  MONEY_500 MONEY_800 MONEY_1000 MONEY_2000
    private SamllPayMoney samllPayMoney ;
    //是否实名认证
    private boolean realNameAuth;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOldPayPassword() {
        return oldPayPassword;
    }

    public void setOldPayPassword(String oldPayPassword) {
        this.oldPayPassword = oldPayPassword;
    }

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

    public String getRandomCode() {
        return randomCode;
    }

    public void setRandomCode(String randomCode) {
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

    public boolean isRealNameAuth() {
        return realNameAuth;
    }

    public void setRealNameAuth(boolean realNameAuth) {
        this.realNameAuth = realNameAuth;
    }


    public static PayAccount voToDomain(PayAccountVo payAccountVo,PayAccount one){
       /* String jsonString = JSONObject.toJSONString(payAccountVo);
        JSON.parseObject(jsonString, PayAccount.class);*/
        PayAccount payAccount = new PayAccount();
        payAccount.setRandomCode(one.getRandomCode());
        payAccount.setPayPassword(payAccountVo.getPayPassword());
        return payAccount;
    }
}
