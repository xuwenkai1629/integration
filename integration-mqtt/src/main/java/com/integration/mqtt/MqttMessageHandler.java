package com.integration.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

/**
 * 描述
 * Title: MqttMessageHandler
 * Description: TODO
 * Copyright: Copyright (c) 2019<br>
 * Company: 齐丰科技股份有限公司<br>
 *
 * @author: xu wen kai
 * date: 2020/7/3
 */
@Slf4j
@Component
public class MqttMessageHandler implements MessageHandler {
    @ServiceActivator(inputChannel = "channel1")
    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        log.info("收到消息---------------{}", message);
    }
}
