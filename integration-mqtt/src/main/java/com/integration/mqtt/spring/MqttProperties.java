package com.integration.mqtt.spring;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * 描述
 * Title: MqttProperties
 * Description: TODO
 * Copyright: Copyright (c) 2019<br>
 * Company: 齐丰科技股份有限公司<br>
 *
 * @author: xu wen kai
 * date: 2020/7/3
 */
@Data
@ConfigurationProperties(prefix = "spring.mqtt")
@Configuration
public class MqttProperties {
    private final Map<String, Config> config;

    @Data
    public static class Config {
        /**
         * 数组tcp://ip:port
         */
        private String[] url;
        /**
         * 超时时间，单位：秒
         */
        private int timeout;
        /**
         * 心跳时间，秒
         */
        private int kepAliveInterval;
        /**
         * qos设置，和topic一一对应
         */
        private int[] qos;
        /**
         * 主题，和qos一一对应
         */
        private String[] topics;
        /**
         * 账号
         */
        private String username;
        /**
         * 密码
         */
        private String password;
        /**
         * clientId前缀，会自动生成唯一后缀
         */
        private String clientIdPrefix;
        /**
         * 是否异步发送消息
         */
        private boolean async;
    }
}
