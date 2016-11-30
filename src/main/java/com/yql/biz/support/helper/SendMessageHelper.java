package com.yql.biz.support.helper;

import com.yql.framework.mq.MessagePublisher;
import com.yql.framework.mq.model.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p> 发消息 </p>
 * @auther simple
 * data 2016/11/30 0030.
 */
@Component
public class SendMessageHelper {
    private static final Logger log = LoggerFactory.getLogger(SendMessageHelper.class);

    @Resource
    private MessagePublisher messagePublisher;

    public void sendMessage(TextMessage textMessage){
        log.debug("============发送消息 start=============");
        messagePublisher.send(textMessage);
    }
}
