package com.integration.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * 描述
 * Title: OnMessageCallback
 * Description: TODO
 * Copyright: Copyright (c) 2019<br>
 * Company: 齐丰科技股份有限公司<br>
 *
 * @author: xu wen kai
 * date: 2020/6/30
 */
@Slf4j
public class OnMessageCallback implements MqttCallback {
    @Override
    public void connectionLost(Throwable throwable) {
        int i = 1;
        MqttClient mqttClient = MqttSingletonConnectionUtil.getConnection();
        while (!mqttClient.isConnected()) {
            log.info("mqtt lost connection try to reconnect;connection count:{}", i);
            mqttClient = MqttSingletonConnectionUtil.getConnection();
        }
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        // subscribe后得到的消息会执行到这里面
        System.out.println("接收消息主题:" + topic);
        System.out.println("接收消息Qos:" + mqttMessage.getQos());
        System.out.println("接收消息内容:" + new String(mqttMessage.getPayload()));
    }

    //在完成消息传递并收到所有确认后调用
    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        System.out.println("deliveryComplete---------" + iMqttDeliveryToken.isComplete());
    }
}
