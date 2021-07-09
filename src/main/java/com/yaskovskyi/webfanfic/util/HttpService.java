package com.yaskovskyi.webfanfic.util;

import com.yaskovskyi.webfanfic.config.WebClientApi;
import com.yaskovskyi.webfanfic.model.Post;
import com.yaskovskyi.webfanfic.model.User;
import net.minidev.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.*;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import reactor.core.publisher.Mono;
import reactor.util.LinkedMultiValueMap;
import reactor.util.MultiValueMap;

import java.net.URI;
import java.util.List;
import java.util.Objects;

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
        Mono<List<Post>> posts = response.bodyToMono(new ParameterizedTypeReference<>() {
        });
//        ResponseSpec response = (ResponseSpec)
        return posts.block();
    }

    public String sendNewUserData(String firstname, String lastname, String username, String email, String password) {
        User user = new User();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setUserName(username);
        user.setEmail(email);
        user.setPassword(password);
        System.out.println(user);
        ResponseSpec response = api.post().uri("/registration/process_register").body(Mono.just(user), User.class).retrieve();

        System.out.println(response + "1) ");
        return api.post().uri("/registration/process_register").body(Mono.just(user), User.class).retrieve().bodyToMono(String.class).block();
    }

    public String loginUser(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        api.post().uri("/login/process_login").body(Mono.just(user), User.class).retrieve().bodyToMono(String.class).block();
        return "";
    }
}
