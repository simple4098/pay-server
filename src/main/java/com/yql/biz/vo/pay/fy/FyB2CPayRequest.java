package com.yql.biz.vo.pay.fy;

import com.yql.biz.enums.fy.FyVersion;
import com.yql.biz.enums.fy.OrderPayType;
import com.yql.biz.util.PayUtil;
import com.yql.biz.vo.pay.request.DjPay;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p> 富友b2c支付的请求对象 </p>
 *
 * @auther simple
 * data 2016/12/7 0007.
 */
@XmlRootElement(name = "xml")
public class FyB2CPayRequest extends DjPay {
    //版本号
    private String ver = FyVersion.B2C_PAY_VERSION.getValue();
    //商户代码
    private String mchntCd;
    //商户订单号
    private String orderId = PayUtil.randomCodeNum(30);
    //支付金额 ‘分’为单位
    private String orderAmt;
    //支付类型
    private String orderPayType = OrderPayType.B2C.name();
    //页面跳转URL 商户接收支付结果通知地址
    private String pageNotifyUrl;
    //后台通知URL 商户接收支付结果后台通知地址
    private String backNotifyUrl;
    //总行代码
    private String issInsCd;
    //超时时间
    private String orderValidTime;
    //商品名称
    private String goodsName;
    //商品展示网址
    private String goodsDisplayUrl;
    //备注
    private String rem;
    //MD5摘要数据
    private String md5;

    public FyB2CPayRequest(String mchntCd, String orderAmt, String pageNotifyUrl, String backNotifyUrl, String issInsCd) {
        this.mchntCd = mchntCd;
        this.orderAmt = orderAmt;
        this.pageNotifyUrl = pageNotifyUrl;
        this.backNotifyUrl = backNotifyUrl;
        this.issInsCd = issInsCd;
    }

    public FyB2CPayRequest() {
    }


    @XmlElement(name = "mchnt_cd")
    public String getMchntCd() {
        return mchntCd;
    }

    public void setMchntCd(String mchntCd) {
        this.mchntCd = mchntCd;
    }

    @XmlElement(name = "ver")
    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    @XmlElement(name = "order_id")
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @XmlElement(name = "order_amt")
    public String getOrderAmt() {
        return orderAmt;
    }

    public void setOrderAmt(String orderAmt) {
        this.orderAmt = orderAmt;
    }

    @XmlElement(name = "order_pay_type")
    public String getOrderPayType() {
        return orderPayType;
    }

    public void setOrderPayType(String orderPayType) {
        this.orderPayType = orderPayType;
    }

    @XmlElement(name = "page_notify_url")
    public String getPageNotifyUrl() {
        return pageNotifyUrl;
    }

    public void setPageNotifyUrl(String pageNotifyUrl) {
        this.pageNotifyUrl = pageNotifyUrl;
    }

    @XmlElement(name = "back_notify_url")
    public String getBackNotifyUrl() {
        return backNotifyUrl;
    }

    public void setBackNotifyUrl(String backNotifyUrl) {
        this.backNotifyUrl = backNotifyUrl;
    }

    @XmlElement(name = "iss_ins_cd")
    public String getIssInsCd() {
        return issInsCd;
    }

    public void setIssInsCd(String issInsCd) {
        this.issInsCd = issInsCd;
    }

    @XmlElement(name = "goods_name")
    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    @XmlElement(name = "goods_display_url")
    public String getGoodsDisplayUrl() {
        return goodsDisplayUrl;
    }

    public void setGoodsDisplayUrl(String goodsDisplayUrl) {
        this.goodsDisplayUrl = goodsDisplayUrl;
    }

    @XmlElement(name = "rem")
    public String getRem() {
        return rem;
    }

    public void setRem(String rem) {
        this.rem = rem;
    }

    @XmlElement(name = "md5")
    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    @XmlElement(name = "order_valid_time")
    public String getOrderValidTime() {
        return orderValidTime;
    }

    public void setOrderValidTime(String orderValidTime) {
        this.orderValidTime = orderValidTime;
    }

    public String toMd5String(String key) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.mchntCd).append("|").append(this.orderId).append("|").append(this.orderAmt).append("|").append(this.orderPayType);
        stringBuffer.append("|").append(this.pageNotifyUrl).append("|").append(this.backNotifyUrl).append("|");
        if (this.orderValidTime != null) {
            stringBuffer.append(this.orderValidTime).append("|");
        }
        stringBuffer.append(this.issInsCd).append("|");
        if (this.goodsName != null) {
            stringBuffer.append(this.goodsName).append("|");
        }
        if (this.goodsDisplayUrl != null) {
            stringBuffer.append(this.getGoodsDisplayUrl()).append("|");
        }
        if (this.rem != null) {
            stringBuffer.append(this.rem).append("|");
        }
        stringBuffer.append(this.ver).append("|").append(key);
        String md5Encode = PayUtil.MD5Encode(stringBuffer.toString(), "UTF-8").toUpperCase();
        return md5Encode;
    }

    public String toParam(String key) {
        String md5String = toMd5String(key);
        StringBuffer stringBuffer = new StringBuffer("?md5=").append(md5String).append("&mchnt_cd=");
        stringBuffer.append(this.mchntCd).append("&").append("order_id=").append(this.orderId).append("&").append("order_amt=").append(this.orderAmt).append("&").append("order_pay_type=").append(this.orderPayType);
        stringBuffer.append("&").append("page_notify_url=").append(this.pageNotifyUrl).append("&").append("back_notify_url=").append(this.backNotifyUrl).append("&");
        if (this.orderValidTime != null) {
            stringBuffer.append("order_valid_time=").append(this.orderValidTime).append("&");
        }
        stringBuffer.append("iss_ins_cd=").append(this.issInsCd).append("&");
        if (this.goodsName != null) {
            stringBuffer.append("goods_name=").append(this.goodsName).append("&");
        }
        if (this.goodsDisplayUrl != null) {
            stringBuffer.append("goods_display_url=").append(this.getGoodsDisplayUrl()).append("&");
        }
        if (this.rem != null) {
            stringBuffer.append("rem=").append(this.rem).append("&");
        }
        stringBuffer.append("ver=").append(this.ver);
        //String md5Encode = PayUtil.MD5Encode(stringBuffer.toString(), "UTF-8").toUpperCase();
        return stringBuffer.toString();
    }
}
