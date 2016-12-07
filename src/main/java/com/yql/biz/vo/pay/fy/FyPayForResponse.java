package com.yql.biz.vo.pay.fy;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p> 富友代发响应对象</p>
 * @auther simple
 * data 2016/12/7 0007.
 */
@XmlRootElement(name = "payforrsp")
public class FyPayForResponse {
    //响应码
    private String ret;
    //响应描述
    private String memo;
    @XmlElement(name = "ret")
    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }
    @XmlElement(name = "memo")
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
