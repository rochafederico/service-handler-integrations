package com.api.integraciones.interfaces;

import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

public interface IntegrationHandler<T, R> {
    HttpMethod getMethod();

    String getUrl(Map<String, Object> params);

    Class<T> getTypeResponse();

    R mapResponse(T response);

    default void handleError(Exception e) {
        System.out.println(e.getClass());
        if (e instanceof HttpStatusCodeException) {
            throw new ResponseStatusException(
                    HttpStatus.valueOf(((HttpStatusCodeException) e).getStatusCode().value()));
        }
    }
}
