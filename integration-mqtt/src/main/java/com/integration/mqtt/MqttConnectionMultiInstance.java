package com.integration.mqtt;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * 描述
 * Title: MqttConnectionMultiInstance
 * Description: TODO
 * Copyright: Copyright (c) 2019<br>
 * Company: 齐丰科技股份有限公司<br>
 *
 * @author: xu wen kai
 * date: 2020/7/1
 */
@Slf4j
@Data
public class MqttConnectionMultiInstance {
    private MqttClient mqttClient;
    private String username;
    private String password;
    private String hostUrl;
    private String clientId;


    public MqttConnectionMultiInstance( String username, String password, String hostUrl, String clientId) {
        this.username = username;
        this.password = password;
        this.hostUrl = hostUrl;
        this.clientId = clientId;
        this.mqttClient = init();
    }

    private  MqttClient init() {
        MemoryPersistence persistence = new MemoryPersistence();
        MqttConnectOptions connOpts;
        MqttClient mqttClient = null;
        try {
            mqttClient = new MqttClient(this.hostUrl, this.clientId, persistence);
            connOpts = new MqttConnectOptions();
            connOpts.setUserName(this.username);
            connOpts.setPassword(this.password.toCharArray());
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
