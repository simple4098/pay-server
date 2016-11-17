package com.yql.biz.vo.pay.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p> 银行卡解绑绑定关系 </p>
 * @auther simple
 * data 2016/11/17 0017.
 */
@XmlRootElement(name = "Response")
public class UninstallBangResponse extends Response{
   private UninstallBangResponseBody uninstallBangResponseBody;

    @XmlElement(name = "Body")
    public UninstallBangResponseBody getUninstallBangResponseBody() {
        return uninstallBangResponseBody;
    }

    public void setUninstallBangResponseBody(UninstallBangResponseBody uninstallBangResponseBody) {
        this.uninstallBangResponseBody = uninstallBangResponseBody;
    }
}
