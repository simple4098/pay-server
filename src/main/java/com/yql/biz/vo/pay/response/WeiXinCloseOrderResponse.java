package com.yql.biz.vo.pay.response;

import com.yql.biz.vo.pay.wx.WeiXinNotifyVo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p> 微信支付【关闭订单】微信返回的对象 </p>
 * @auther simple
 * data 2016/11/16 0016.
 */
@XmlRootElement(name = "xml")
public class WeiXinCloseOrderResponse extends WeiXinNotifyVo {

}
