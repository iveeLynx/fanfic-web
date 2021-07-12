package com.yaskovskyi.webfanfic.util;

import com.yaskovskyi.webfanfic.configuration.WebClientApi;
import com.yaskovskyi.webfanfic.model.Post;
import com.yaskovskyi.webfanfic.model.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.*;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import reactor.core.publisher.Mono;

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

    public String sendNewUserData(String firstname, String lastname, String username, String email, String password) {
        User user = new User();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setUserName(username);
        user.setEmail(email);
        user.setPassword(password);
        return api.post().uri("/registration/process_register").body(Mono.just(user), User.class).retrieve().bodyToMono(String.class).block();
    }

    public User getUser(String username) {
        return api.post().uri("/login/check_user").body(Mono.just(username), String.class).retrieve().bodyToMono(User.class).block();
    }

    public Post getPost(String id) {
        Post post = api.post().uri("/posts/reading").body(Mono.just(id), String.class).retrieve().bodyToMono(Post.class).block();
        return post;
    }
}
