package com.example.springsecurity.global.security.jwt;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private final String secret;
    private final long accessExpMin;
    private final String header;
    private final String prefix;

    public JwtProperties(String secret, long accessExpMin, String header, String prefix) {
        this.secret = secret;
        this.accessExpMin = accessExpMin;
        this.header = header;
        this.prefix = prefix;
    }
}
