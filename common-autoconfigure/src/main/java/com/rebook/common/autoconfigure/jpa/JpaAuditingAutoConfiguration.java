package com.rebook.common.autoconfigure.jpa;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@AutoConfiguration
@ConditionalOnClass(EnableJpaAuditing.class)
@EntityScan(basePackages = "com.example")
@EnableJpaRepositories(basePackages = "com.example")
public class JpaAuditingAutoConfiguration {

    @Configuration
    @EnableJpaAuditing
    public static class JpaAuditingConfig {
    }
}
