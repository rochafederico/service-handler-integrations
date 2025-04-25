package com.api.integraciones.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "integraciones.api.typicode")
@Data
public class TypicodeProperties {
    private String url;
    private String postsPath;
}
