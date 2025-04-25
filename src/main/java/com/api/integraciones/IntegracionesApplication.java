package com.api.integraciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.api.integraciones.config.TypicodeProperties;

@SpringBootApplication
@EnableConfigurationProperties(TypicodeProperties.class)
public class IntegracionesApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntegracionesApplication.class, args);
    }
}