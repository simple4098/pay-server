package com.yql.biz.support;

import com.yql.biz.conf.ApplicationConf;
import com.yql.biz.enums.ListenerTagType;
import com.yql.biz.support.helper.AccountMsgEventListener;
import com.yql.biz.support.helper.IConsumerMsgEventListener;
import com.yql.framework.mq.listener.MessageListener;
import com.yql.framework.mq.model.MqMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 账户中心的消息监听
 * creator simple
 * data 2016/11/8 0008.
 */
@Component
public class AccountMsgListener implements MessageListener {
    private static final Logger logger = LoggerFactory.getLogger(AccountMsgListener.class);

    @Resource
    private ApplicationConf applicationConf;
    @Resource
    private AccountMsgEventListener accountMsgEventListener;

    @Override
    public String onMessage(MqMessage message) {
        accountMsgEventListener.eventHandling(message);
        logger.debug("============account 消息监听器 account================：");
        return MqMessage.SUCCESS;
    }

    @Override
    public String getTopic() {
        return applicationConf.getAccountListenerTopic();
    }

    @Override
    public String getTag() {
        //return ListenerTagType.USER_CENTER_USER_REAL_NAME_AUTH.name()+"||"+ListenerTagType.USER_CENTER_USER_REGISTER.name();
        return applicationConf.getAccountListenerTag();
    }
}
