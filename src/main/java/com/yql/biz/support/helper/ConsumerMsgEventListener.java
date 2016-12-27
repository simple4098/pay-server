package com.yql.biz.support.helper;

import com.yql.biz.enums.ListenerTagType;
import com.yql.biz.model.PayAccount;
import com.yql.biz.service.IPayAccountService;
import com.yql.framework.mq.model.MqMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p> 事件处理实现 </p>
 * @auther simple
 * data 2016/11/22 0022.
 */
@Component
public class ConsumerMsgEventListener implements IConsumerMsgEventListener {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerMsgEventListener.class);
    @Resource
    private IPayAccountService payAccountService;
    @Resource
    private IPayAccountServiceHelper payAccountServiceHelper;

    @Override
    @Transactional
    public void eventHandling(MqMessage message) {
        //用户注册
        if (message.getTag().equals(ListenerTagType.USER_CENTER_USER_REGISTER.name())){
            logger.debug("用户注册userCode:"+message.getBodyAsText());
            PayAccount payAccount = payAccountService.findByUserCode(message.getBodyAsText());
            if (payAccount==null){
                payAccount = new PayAccount();
            }
            payAccount.setUserCode(message.getBodyAsText());
            payAccountService.savePayAccount(payAccount);
        //用户实名认证
        }else if (message.getTag().equals(ListenerTagType.USER_CENTER_AUTH_REAL_NAME.name())){
            logger.debug("用户实名认证userCode:"+message.getBodyAsText());
            payAccountServiceHelper.updatePayAccountRelName(message.getBodyAsText());
        }
    }
}
