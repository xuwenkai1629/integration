package com.integration.minio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 描述
 * Title: MinioProp
 * Description: TODO
 * Copyright: Copyright (c) 2019<br>
 * Company: 齐丰科技股份有限公司<br>
 *
 * @author: xu wen kai
 * date: 2020/7/14
 */
@Data
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioProp {
    /**
     * 连接url
     */
    private String endpoint;
    /**
     * 用户名
     */
    private String accesskey;
    /**
     * 密码
     */
    private String secretKey;
}
