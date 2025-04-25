package com.api.integraciones.handlers.posts;

import com.api.integraciones.config.TypicodeProperties;
import com.api.integraciones.dtos.posts.PostDto;
import com.api.integraciones.dtos.posts.PostsResponseDto;
import com.api.integraciones.interfaces.IntegrationHandler;
import com.api.integraciones.interfaces.ServiceHandler;
import com.api.integraciones.models.posts.PostModel;
import com.api.integraciones.models.posts.PostsResponseModel;

import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@ServiceHandler(value = "typicode.posts")
public class ObtenerPostsIntegration implements IntegrationHandler<PostsResponseModel, PostsResponseDto> {

    private final TypicodeProperties typicodeProperties;

    public ObtenerPostsIntegration(TypicodeProperties typicodeProperties) {
        this.typicodeProperties = typicodeProperties;
    }

    @Override
    public HttpMethod getMethod() {
        return HttpMethod.GET;
    }

    @Override
    public String getUrl(Map<String, Object> params) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(typicodeProperties.getUrl())
                .path(typicodeProperties.getPostsPath());
        if (params != null && !params.isEmpty() && params.containsKey("user_id")) {
            uriBuilder.queryParam("userId", params.get("user_id"));
        }

        return uriBuilder.build().toUriString();
    }

    @Override
    public Class<PostsResponseModel> getTypeResponse() {
        return PostsResponseModel.class;
    }

    @Override
    public PostsResponseDto mapResponse(PostsResponseModel response) {
        PostsResponseDto result = new PostsResponseDto();
        for (PostModel posts : response) {
            result.add(PostDto.builder()
                    .id(posts.getId())
                    .title(posts.getTitle())
                    .build());
        }
        return result;
    }
}
