package com.api.integraciones.services;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.api.integraciones.interfaces.IntegrationHandler;

@Component
public class IntegrationExecutor {

    private final RestTemplate restTemplate;

    public IntegrationExecutor(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T, R> R execute(IntegrationHandler<T, R> handler, Map<String, Object> params) {
        HttpMethod httpMethod = handler.getMethod();
        HttpEntity<?> requestEntity = buildRequestEntity(params, httpMethod);

        ResponseEntity<T> response = restTemplate.exchange(handler.getUrl(params), handler.getMethod(),
                requestEntity,
                handler.getTypeResponse());
        return handler.mapResponse(response.getBody());
    }

    private HttpEntity<?> buildRequestEntity(Map<String, Object> params, HttpMethod method) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (method == HttpMethod.GET) {
            return new HttpEntity<>(headers);
        } else {
            return new HttpEntity<>(params, headers);
        }
    }
}