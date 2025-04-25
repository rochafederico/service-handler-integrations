package com.api.integraciones.dtos.posts;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostDto {
    private int id;
    private String title;
}
