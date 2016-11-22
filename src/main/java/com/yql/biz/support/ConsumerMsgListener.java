package com.yql.biz.support;

import com.alibaba.fastjson.JSON;
import com.yql.biz.support.helper.IConsumerMsgEventListener;
import com.yql.framework.mq.listener.MessageListener;
import com.yql.framework.mq.model.MqMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 消息监听器
 * creator simple
 * data 2016/11/8 0008.
 */
@Component
public class ConsumerMsgListener implements MessageListener {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerMsgListener.class);
    @Resource
    private IConsumerMsgEventListener consumerMsgEventListener;


    @Override
    public String onMessage(MqMessage message) {
        logger.debug("监听:"+ JSON.toJSONString(message)+":"+message.getBodyAsText());
        consumerMsgEventListener.eventHandling(message);
        logger.debug("============end 消息监听器 end================：");
        return "SUCCESS";
    }

    @Override
    public String getTopic() {
        return "TEST_USER_REGISTER";
    }

    @Override
    public String getTag() {
        return "*";
    }
}
