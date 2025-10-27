package com.example.springsecurity.global.security.jwt;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private final String secret;
    private final long accessExpiration;
    private final long refreshExpiration;
    private final String header;
    private final String prefix;

    public JwtProperties(String secret, long accessExpiration, long refreshExpiration, String header, String prefix) {
        this.secret = secret;
        this.accessExpiration = accessExpiration;
        this.refreshExpiration = refreshExpiration;
        this.header = header;
        this.prefix = prefix;
    }
}
