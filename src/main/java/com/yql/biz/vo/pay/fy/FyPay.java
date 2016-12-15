package com.yql.biz.vo.pay.fy;

import com.yql.biz.enums.fy.FyVersion;
import com.yql.biz.vo.pay.request.DjPay;

import javax.xml.bind.annotation.XmlElement;

/**
 * <p> 富友支付相关类的超类 </p>
 * @auther simple
 * data 2016/12/6 0006.
 */
public class FyPay extends DjPay {
    //版本号
    private String ver = FyVersion.CHECK_BANK_VERSION_130.getValue();
    //MD5摘要数据
    private String sign;
    @XmlElement(name = "Ver")
    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }
    @XmlElement(name = "Sign")
    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
