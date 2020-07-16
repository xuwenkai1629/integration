package com.integration.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.util.StringUtils;

/**
 * 描述
 * Title: MqttClientUtil
 * Description: TODO
 * Copyright: Copyright (c) 2019<br>
 * Company: 齐丰科技股份有限公司<br>
 *
 * @author: xu wen kai
 * date: 2020/7/1
 */
@Slf4j
public class MqttClientUtil {
    /**
     * 订阅单个主题
     *
     * @param client mqtt连接
     * @param topic  主题
     * @param qos    0：最多一次的传输 1：至少一次的传输 2：只有一次的传输
     * @return void
     * @author xu wen kai
     * @create: 2020/7/1 10:55
     */
    public static void subscribe(MqttClient client, String topic, int qos) {
        try {
            client.subscribe(topic, qos);
        } catch (MqttException e) {
            log.error("subscription failed errorMsg:{}", e.getMessage());
        }
    }

    /**
     * 订阅多个主题
     *
     * @param client   mqtt连接
     * @param topicArr 主题
     * @param qosArr   0：最多一次的传输 1：至少一次的传输 2：只有一次的传输
     * @return void
     * @author xu wen kai
     * @create: 2020/7/1 10:57
     */
    public static void subscribeArr(MqttClient client, String[] topicArr, int[] qosArr) {
        try {
            client.subscribe(topicArr, qosArr);
        } catch (MqttException e) {
            log.error("subscription failed errorMsg:{}", e.getMessage());
        }
    }

    /**
     * 发布消息
     *
     * @param client   mqtt连接
     * @param pubTopic 主题
     * @param message  消息
     * @param qos      0：最多一次的传输 1：至少一次的传输 2：只有一次的传输
     * @return void
     * @author xu wen kai
     * @create: 2020/7/1 11:24
     */
    public static void publishMessage(MqttClient client, String pubTopic, String message, int qos) {
        if (null == client || !client.isConnected()) {
            log.error("mqttClient is lostConnection");
            throw new RuntimeException("mqtt lostConnection.");
        }
        if (StringUtils.isEmpty(pubTopic) || StringUtils.isEmpty(message)) {
            throw new RuntimeException("topic and message cannot be empty.");
        }
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setQos(qos);
        mqttMessage.setPayload(message.getBytes());
        MqttTopic topic = client.getTopic(pubTopic);
        if (null != topic) {
            try {
                MqttDeliveryToken mqttDeliveryToken =  topic.publish(mqttMessage);
                mqttDeliveryToken.waitForCompletion();
            } catch (MqttException e) {
                log.error("mqtt failed to send message errorMsg:{}", e.getMessage());
            }
        }
    }

    /**
     * 清除主题
     *
     * @param client   mqtt连接
     * @param pubTopic 主题
     * @return void
     * @author xu wen kai
     * @create: 2020/7/1 11:28
     */
    public static void cleanTopic(MqttClient client, String pubTopic) {
        if (null != client && !client.isConnected()) {
            try {
                client.unsubscribe(pubTopic);
            } catch (MqttException e) {
                log.error("Failed to clear topic;errorMsg:{}", e.getMessage());
            }
        } else {
            log.error("mqttClient is lostConnection");
        }
    }

    public static void main(String[] args) {
        MqttClient mqttClient = MqttSingletonConnectionUtil.getConnection();
        subscribe(mqttClient,"testtopic/1",2);
        String message = "{ \"msg\": \"hello\" }";
        publishMessage(mqttClient,"testtopic/1",message,2);
    }
}
