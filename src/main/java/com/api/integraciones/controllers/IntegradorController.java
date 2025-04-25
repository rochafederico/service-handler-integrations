package com.api.integraciones.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.api.integraciones.dtos.IntegrationRequestDto;
import com.api.integraciones.interfaces.IntegrationHandler;
import com.api.integraciones.services.IntegrationExecutor;
import com.api.integraciones.services.IntegrationHandlerRegistry;

@RestController
@RequestMapping("/integrador")
public class IntegradorController {

    private final IntegrationExecutor executor;
    private final IntegrationHandlerRegistry registry;

    public IntegradorController(IntegrationExecutor executor, IntegrationHandlerRegistry registry) {
        this.executor = executor;
        this.registry = registry;
    }

    @PostMapping
    public ResponseEntity<?> integrar(@RequestBody IntegrationRequestDto request) {
        String serviceHandler = request.getServiceHandler();
        Map<String, Object> params = (request.getParams() != null) ? request.getParams() : Map.of();

        IntegrationHandler<?, ?> handler = registry.getHandler(serviceHandler);
        if (handler == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No se encontró el handler: ".concat(serviceHandler));
        }

        try {
            Object response = executor.execute(handler, params);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            handler.handleError(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al procesar la integración"));
        }
    }
}
