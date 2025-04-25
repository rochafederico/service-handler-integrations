package com.api.integraciones.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Map;

@Data
public class IntegrationRequestDto {
    @JsonProperty("service_handler")
    private String serviceHandler;
    private Map<String, Object> params;
}
