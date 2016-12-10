package com.yql.biz.vo;

import com.yql.biz.model.PayBank;
import com.yql.biz.model.PayOrderAccount;

import java.math.BigDecimal;

/**
 * <p> 提现列表对象 </p>
 * @auther simple
 * data 2016/12/2 0002.
 */
public class DrawMoneyVo {

    private String userCode;
    //支付/提现订单号
    private String orderNo;
    //持卡人
    private String cardholder;
    //体现金额
    private BigDecimal totalPrice;
    //银行卡号
    private String bankCard;
    //银行卡名称
    private String bankName;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCardholder() {
        return cardholder;
    }

    public void setCardholder(String cardholder) {
        this.cardholder = cardholder;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public static DrawMoneyVo toVo(PayOrderAccount order, PayBank p) {
        DrawMoneyVo drawMoneyVo = new DrawMoneyVo();
        drawMoneyVo.setOrderNo(order.getOrderNo());
        drawMoneyVo.setCardholder(p.getCardholder());
        drawMoneyVo.setTotalPrice(order.getTotalPrice());
        drawMoneyVo.setBankCard(p.getBankCard());
        drawMoneyVo.setBankName(p.getBankName());
        return drawMoneyVo;
    }
}
