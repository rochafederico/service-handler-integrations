package com.api.integraciones.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.api.integraciones.interfaces.IntegrationHandler;
import com.api.integraciones.interfaces.ServiceHandler;

@Component
public class IntegrationHandlerRegistry {
    private final Map<String, IntegrationHandler<?, ?>> handlers = new HashMap<>();

    public IntegrationHandlerRegistry(List<IntegrationHandler<?, ?>> beans) {
        for (IntegrationHandler<?, ?> bean : beans) {
            ServiceHandler annotation = bean.getClass().getAnnotation(ServiceHandler.class);
            if (annotation != null) {
                handlers.put(annotation.value(), bean);
            }
        }
    }

    public IntegrationHandler<?, ?> getHandler(String key) {
        return handlers.get(key);
    }
}
