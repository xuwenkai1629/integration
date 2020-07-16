package com.integration.mqtt;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

import java.util.Properties;

/**
 * 描述
 * Title: YmlUtil
 * Description: TODO
 * Copyright: Copyright (c) 2019<br>
 * Company: 齐丰科技股份有限公司<br>
 *
 * @author: xu wen kai
 * date: 2020/6/30
 */
public class YmlUtil {
    private static Properties properties;

    static {
        Resource resource = new ClassPathResource("application.yml");
        YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
        yamlFactory.setResources(resource);
        properties = yamlFactory.getObject();
    }

    public static Object getYmlValue(String key) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        return properties.get(key);
    }
}
