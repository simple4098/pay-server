package com.yql.biz.vo.pay.fy;

import com.yql.biz.vo.pay.request.DjPay;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p> 富友下单返回对象 </p>
 *
 * @auther simple
 * data 2016/12/7 0007.
 */
@XmlRootElement(name = "FM")
public class FyOrderResponse extends DjPay {
    //返回代码
    private String rcd;
    //返回详情
    private String rDesc;
    //订单号
    private String orderId;
    //MD5摘要数据
    private String sign;

    @XmlElement(name = "Rcd")
    public String getRcd() {
        return rcd;
    }

    public void setRcd(String rcd) {
        this.rcd = rcd;
    }

    @XmlElement(name = "RDesc")
    public String getrDesc() {
        return rDesc;
    }

    public void setrDesc(String rDesc) {
        this.rDesc = rDesc;
    }

    @XmlElement(name = "OrderId")
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @XmlElement(name = "Sign")
    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
