package com.yql.biz.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

/**
 * 消息监听器
 * creator simple
 * data 2016/11/8 0008.
 */
public class ConsumerMsgListener implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerMsgListener.class);
    @Override
    public void run(String... args) throws Exception {
        logger.debug("========================监听启动=======================");
    }
}
