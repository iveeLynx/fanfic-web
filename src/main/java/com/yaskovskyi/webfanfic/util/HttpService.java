package com.yaskovskyi.webfanfic.util;

import com.yaskovskyi.webfanfic.config.WebClientApi;
import com.yaskovskyi.webfanfic.model.Post;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.*;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

public class HttpService {

    WebClient api;

    private static final HttpService INSTANCE = new HttpService();

    private HttpService() {
        api = WebClientApi.getInstance().buildApi();
    }

    public static HttpService getInstance() {
        return INSTANCE;
    }

    public List<Post> getPosts() {
        ResponseSpec response = api.get().uri("/posts/show").accept(MediaType.APPLICATION_JSON).retrieve();
        Mono<List<Post>> posts = response.bodyToMono(new ParameterizedTypeReference<>() {});
        return posts.block();
    }

}
