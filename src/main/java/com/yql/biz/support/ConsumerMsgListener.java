package com.yql.biz.support;

import com.yql.framework.mq.listener.MessageListener;
import com.yql.framework.mq.model.MqMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 消息监听器
 * creator simple
 * data 2016/11/8 0008.
 */
@Component
public class ConsumerMsgListener implements MessageListener {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerMsgListener.class);



    @Override
    public String onMessage(MqMessage message) {
        logger.debug("============ 消息监听器 "+message.getBodyAsText());
        if (message.getKey().equals("user")){

        }
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
