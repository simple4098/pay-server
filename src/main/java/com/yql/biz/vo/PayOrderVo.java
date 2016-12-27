package com.yql.biz.vo;

import com.yql.biz.enums.PayType;
import com.yql.biz.model.PayOrderAccount;
import com.yql.biz.util.PayUtil;
import com.yql.biz.vo.pay.response.PayMessageValidateResponseBody;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * creator simple
 * data 2016/11/10 0010.
 */
public class PayOrderVo {
    @NotNull(message = "{com.yql.validation.constraints.userCode.message}")
    private String userCode;
    private Integer payAccountId;
    //支付/提现订单号
    @NotNull(message = "{com.yql.validation.constraints.OrderNo.message}")
    private String orderNo;
    //支付号（系统生产）
    private String payNo;
    //第三方支付返回的订单号
    private String payOrder;
    //对方userCode
    private String otherUserCode;
    //支付类型
    @NotNull(message = "{com.yql.validation.constraints.PayType.message}")
    private PayType payType;
    //持卡人
    private String cardholder;
    //交易码
   /* @NotNull(message = "{com.yql.validation.constraints.txCode.notnull}")*/
    private String txCode;
    //支付总金额
    @NotNull(message = "{com.yql.validation.constraints.totalPrice.message}")
    private BigDecimal totalPrice;
    //支付信息
    private String msg;
    //交易状态 10=处理中 20=支付成功 30=支付失败 40=微信预付单
    private Integer payStatus;
    //应该处理时间
    private Date bankTxTime;
    //备注
    private String remark;
    private Integer payBankId;
    //用户ip
    private String spbillCreateIp;
    //微信响应内容
    //private WeiXinAppRequest appRequest;
    //支付密码
    private String payPassword;
    //收款人账户code
    private String payeeCode;
    //付款人账户code
    private String payerCode;

    public String getPayeeCode() {
        return payeeCode;
    }

    public void setPayeeCode(String payeeCode) {
        this.payeeCode = payeeCode;
    }

    public String getPayerCode() {
        return payerCode;
    }

    public void setPayerCode(String payerCode) {
        this.payerCode = payerCode;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public Integer getPayBankId() {
        return payBankId;
    }

    public void setPayBankId(Integer payBankId) {
        this.payBankId = payBankId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Integer getPayAccountId() {
        return payAccountId;
    }

    public void setPayAccountId(Integer payAccountId) {
        this.payAccountId = payAccountId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public String getPayOrder() {
        return payOrder;
    }

    public void setPayOrder(String payOrder) {
        this.payOrder = payOrder;
    }

    public String getOtherUserCode() {
        return otherUserCode;
    }

    public void setOtherUserCode(String otherUserCode) {
        this.otherUserCode = otherUserCode;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public String getCardholder() {
        return cardholder;
    }

    public void setCardholder(String cardholder) {
        this.cardholder = cardholder;
    }

    public String getTxCode() {
        return txCode;
    }

    public void setTxCode(String txCode) {
        this.txCode = txCode;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer totalFee(){
        if ( this.totalPrice!=null){
            return PayUtil.priceToCent(this.totalPrice);
        }
        return null;
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Date getBankTxTime() {
        return bankTxTime;
    }

    public void setBankTxTime(Date bankTxTime) {
        this.bankTxTime = bankTxTime;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    /*public WeiXinAppRequest getAppRequest() {
        return appRequest;
    }

    public void setAppRequest(WeiXinAppRequest appRequest) {
        this.appRequest = appRequest;
    }*/

    public static PayOrderAccount toDomain(PayOrderVo payOrderVo) {
        PayOrderAccount payOrderAccount = new PayOrderAccount();
        BeanUtils.copyProperties(payOrderVo,payOrderAccount);
        return payOrderAccount;
    }

    public static PayOrderVo domainToVo(PayOrderAccount result) {
        PayOrderVo payOrderVo = new PayOrderVo();
        BeanUtils.copyProperties(result,payOrderVo);
        return payOrderVo;
    }

    /**
     * 转化成前端有用的参数
     * @param result 支付持久化对象
     */
    public static ResultPayOrder toResultOrder(PayOrderVo result) {
        ResultPayOrder payOrder = new ResultPayOrder();
        BeanUtils.copyProperties(result,payOrder);
        payOrder.setPayPrice(result.getTotalPrice());
        return payOrder;
    }

    /**
     *
     * @param responseBody 支付平台返回对象
     * @param payOrderVo 支付系统产生支付对象
     */
    public static ResultPayOrder toSendResultOrder(PayMessageValidateResponseBody responseBody, PayOrderVo payOrderVo) {
        if (responseBody!=null){
            ResultPayOrder resultPayOrder = new ResultPayOrder();
            resultPayOrder.setPayNo(payOrderVo.getPayNo());
            resultPayOrder.setMsg(responseBody.getResponseMessage());
            resultPayOrder.setPayStatus(responseBody.getStatus());
            resultPayOrder.setPayPrice(payOrderVo.getTotalPrice());
            resultPayOrder.setOrderNo(payOrderVo.getOrderNo());
            resultPayOrder.setTxCode(payOrderVo.getTxCode());
            return  resultPayOrder;
        }
        return null;
    }
}
