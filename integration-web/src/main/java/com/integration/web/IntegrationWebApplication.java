package com.integration.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.integration.*")
public class IntegrationWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntegrationWebApplication.class, args);
    }

}
