package com.example.ratra.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="spring.jwt")
@Data
//Ovdje sam našao ovu anotaciju https://docs.spring.io/spring-boot/docs/1.0.1.RELEASE/reference/html/boot-features-external-config.html
public class JwtSecretHolder {
    private String secret;
    private int expiration;
}
