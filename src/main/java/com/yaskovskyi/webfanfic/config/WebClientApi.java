package com.yaskovskyi.webfanfic.config;

//import java.net.http.HttpClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import org.springframework.web.reactive.function.client.WebClient.UriSpec;

//@javax.inject.Singleton
public class WebClientApi {


    public static final WebClientApi INSTANCE = new WebClientApi();

    public static WebClientApi getInstance() {
        return INSTANCE;
    }

    private static final String URL = "https://polar-earth-68195.herokuapp.com";

    private HttpClient httpClient() {

        return HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .responseTimeout(Duration.ofMillis(5000))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));
    }

    public WebClient buildApi() {
        return WebClient.builder()
                .baseUrl(URL).clientConnector(new ReactorClientHttpConnector(httpClient()))
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", URL))
                .build();
    }

    public UriSpec<WebClient.RequestBodySpec> prepareRequest() {
        return buildApi().post();

    }

}
