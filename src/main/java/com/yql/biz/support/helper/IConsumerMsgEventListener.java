package com.yql.biz.support.helper;

import com.yql.framework.mq.model.MqMessage;

/**
 * <p> 事件处理接口 </p>
 * @auther simple
 * data 2016/11/22 0022.
 */
public interface IConsumerMsgEventListener {

    /**
     * 事件处理器
     * @param mqMessage 接受到的事件消息
     */
    void eventHandling(MqMessage mqMessage);

}
