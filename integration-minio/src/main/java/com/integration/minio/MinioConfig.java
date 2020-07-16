package com.integration.minio;

import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述
 * Title: MinioConfig
 * Description: TODO
 * Copyright: Copyright (c) 2019<br>
 * Company: 齐丰科技股份有限公司<br>
 *
 * @author: xu wen kai
 * date: 2020/7/14
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(value = MinioProp.class)
public class MinioConfig {
    @Autowired
    private MinioProp minioProp;


    @Bean
    public MinioClient minioClient() {
        log.info("######################初始化minio客户端###########################");
        return MinioClient.builder().endpoint(minioProp.getEndpoint()).credentials(minioProp.getAccesskey(),minioProp.getSecretKey()).build();
    }
}
