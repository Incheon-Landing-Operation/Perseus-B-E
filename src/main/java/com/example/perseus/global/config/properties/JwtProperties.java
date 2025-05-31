package com.example.perseus.global.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("jwt")
public record JwtProperties (
        Long accessTime,
        Long refreshTime,
        String prefix,
        String header,
        String secretKey
) {
}
