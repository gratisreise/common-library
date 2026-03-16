package com.rebook.common.autoconfigure.discovery;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

@AutoConfiguration
@ConditionalOnClass(EnableDiscoveryClient.class)
public class DiscoveryClientAutoConfiguration {

    @Configuration
    @EnableDiscoveryClient
    public static class DiscoveryClientConfig {
    }
}
