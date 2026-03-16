package com.rebook.common.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;


import java.util.List;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PassportWebConfig implements WebMvcConfigurer {

    private final PassportUserResolver passportUserResolver;

    public PassportWebConfig(PassportUserResolver passportUserResolver) {
        this.passportUserResolver = passportUserResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(passportUserResolver);
    }
}
