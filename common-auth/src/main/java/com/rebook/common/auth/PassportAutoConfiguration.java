package com.rebook.common.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PassportAutoConfiguration {

    @Bean
    public HmacUtil hmacUtil(
            @Value("${passport.secret-key}") String secretKey,
            @Value("${passport.hmac-algorithm:HmacSHA256}") String hmacAlgorithm
    ) {
        return new HmacUtil(secretKey, hmacAlgorithm);
    }

    @Bean
    public PassportDecoder passportDecoder(HmacUtil hmacUtil) {
        return new PassportDecoder(hmacUtil);
    }

    @Bean
    public PassportUserResolver passportUserResolver(
            PassportDecoder passportDecoder,
            @Value("${passport.header-name:X-Passport}") String headerName
    ) {
        return new PassportUserResolver(passportDecoder, headerName);
    }
}
