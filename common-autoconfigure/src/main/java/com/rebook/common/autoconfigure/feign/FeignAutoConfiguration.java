package com.rebook.common.autoconfigure.feign;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@AutoConfiguration
@ConditionalOnClass(EnableFeignClients.class)
public class FeignAutoConfiguration {

    @Configuration
    @EnableFeignClients(basePackages = "com.example")
    public static class FeignConfig {
    }
}
