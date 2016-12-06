package com.yql.biz.support;

import com.yql.biz.conf.ApplicationConf;
import com.yql.biz.enums.ListenerTagType;
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
    private ApplicationConf applicationConf;
    @Resource
    private IConsumerMsgEventListener consumerMsgEventListener;


    @Override
    public String onMessage(MqMessage message) {
        consumerMsgEventListener.eventHandling(message);
        logger.debug("============end 消息监听器 end================：");
        return MqMessage.SUCCESS;
    }

    @Override
    public String getTopic() {
        return applicationConf.getListenerTopic();
    }

    @Override
    public String getTag() {
        return ListenerTagType.USER_CENTER_USER_REAL_NAME_AUTH.name()+"||"+ListenerTagType.USER_CENTER_USER_REGISTER.name();
    }
}
