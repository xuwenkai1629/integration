package com.integration.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Component;

/**
 * 描述
 * Title: MqttSingletonConnectionUtil
 * Description: TODO
 * Copyright: Copyright (c) 2019<br>
 * Company: 齐丰科技股份有限公司<br>
 *
 * @author: xu wen kai
 * @date: 2020/6/30
 */
@Component
@Slf4j
public class MqttSingletonConnectionUtil {

    private static MqttClient mqttClient;

    private MqttSingletonConnectionUtil() {
    }


    public static MqttClient getConnection(){
        if(mqttClient == null) {
            synchronized (MqttClient.class) {
                if(mqttClient == null) {
                    mqttClient = getCon();
                }
            }
        }
        return mqttClient;
    }

    public static MqttClient getCon(){
        MemoryPersistence persistence = new MemoryPersistence();
        MqttConnectOptions connOpts;
        String mqttServerUri = String.valueOf(YmlUtil.getYmlValue("spring.mqtt.host-url"));
        String clientId = String.valueOf(YmlUtil.getYmlValue("spring.mqtt.host-url"));
        String username = String.valueOf(YmlUtil.getYmlValue("spring.mqtt.username"));
        String password = String.valueOf(YmlUtil.getYmlValue("spring.mqtt.mq-password"));
        int completionTimeout = Integer.parseInt(String.valueOf(YmlUtil.getYmlValue("spring.mqtt.completionTimeout")));
        int keepAlive = Integer.parseInt(String.valueOf(YmlUtil.getYmlValue("spring.mqtt.keepAlive")));
        try {
            mqttClient = new MqttClient(mqttServerUri, clientId, persistence);
            connOpts = new MqttConnectOptions();
            connOpts.setUserName(username);
            connOpts.setPassword(password.toCharArray());
            //连接超时时间
            connOpts.setConnectionTimeout(completionTimeout);
            //心跳
            connOpts.setKeepAliveInterval(keepAlive);
            //保留会话
            connOpts.setCleanSession(true);
            connOpts.setAutomaticReconnect(true);
            mqttClient.setCallback(new OnMessageCallback());
            mqttClient.connect(connOpts);
            log.info("mqtt connected successfully");
        } catch (MqttException e) {
            log.error("mqtt初始化连接失败");
        }
        return mqttClient;
    }


}
