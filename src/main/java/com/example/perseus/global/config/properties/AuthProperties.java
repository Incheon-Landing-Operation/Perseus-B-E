package com.example.perseus.global.config.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("auth")
public record AuthProperties (
        String clientId,
        String redirectUri
) {

}
