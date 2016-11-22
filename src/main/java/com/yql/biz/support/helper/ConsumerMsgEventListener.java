package com.yql.biz.support.helper;

import com.yql.biz.enums.EventTypeKey;
import com.yql.biz.model.PayAccount;
import com.yql.biz.service.IPayAccountService;
import com.yql.framework.mq.model.MqMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p> 事件处理实现 </p>
 * @auther simple
 * data 2016/11/22 0022.
 */
@Component
public class ConsumerMsgEventListener implements IConsumerMsgEventListener {

    @Resource
    private IPayAccountService payAccountService;
    @Resource
    private IPayAccountServiceHelper payAccountServiceHelper;

    @Override
    public void eventHandling(MqMessage message) {
        //用户注册
        if (EventTypeKey.USER_REGISTER.name().equals(message.getKey())){
            PayAccount payAccount = payAccountService.findByUserCode(message.getBodyAsText());
            if (payAccount==null){
                payAccount = new PayAccount();
            }
            payAccount.setUserCode(message.getBodyAsText());
            payAccountService.savePayAccount(payAccount);
        //用户实名认证
        }else if (EventTypeKey.USER_REAL_NAME_AUTH.name().equals(message.getKey())){
            payAccountServiceHelper.updatePayAccountRelName(message.getBodyAsText());
        }
    }
}
