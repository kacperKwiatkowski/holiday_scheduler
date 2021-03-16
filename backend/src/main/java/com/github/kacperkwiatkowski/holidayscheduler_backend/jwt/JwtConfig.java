package com.github.kacperkwiatkowski.holidayscheduler_backend.jwt;

import com.google.common.net.HttpHeaders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
@ConfigurationProperties(prefix = "application.jwt")
public class JwtConfig {

    private String secretKey;
    private String tokenPrefix;
    private Integer tokenExpirationAfterDays;

    JwtConfig() {
    }

    String getSecretKey() {
        return secretKey;
    }

    void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    String getTokenPrefix() {
        return tokenPrefix;
    }

    void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    Integer getTokenExpirationAfterDays() {
        return tokenExpirationAfterDays;
    }

    void setTokenExpirationAfterDays(Integer tokenExpirationAfterDays) {
        this.tokenExpirationAfterDays = tokenExpirationAfterDays;
    }

    String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }
}
