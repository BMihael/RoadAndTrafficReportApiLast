package com.example.ratra.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="spring.jwt")
@Data
public class JwtSecretHolder {
    private String secret;
    private int expiration;
}
