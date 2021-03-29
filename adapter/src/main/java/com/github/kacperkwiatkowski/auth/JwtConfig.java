package com.github.kacperkwiatkowski.auth;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

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
