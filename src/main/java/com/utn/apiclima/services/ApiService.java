package com.utn.apiclima.services;

import com.utn.apiclima.models.ApiResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@Slf4j
public class ApiService {

    @CircuitBreaker(name = "api", fallbackMethod = "fallback")
    public ApiResponse getClimaNow() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://weatherbit-v1-mashape.p.rapidapi.com/alerts?lat=-38.0&lon=-57.5"))
                .header("x-rapidapi-key", "946b2b343amshaee278d5679a355p16d82fjsn566c193fb916")
                .header("x-rapidapi-host", "weatherbit-v1-mashape.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        ApiResponse apiResponse = new Gson().fromJson(response.body(), ApiResponse.class);
        apiResponse.setHora();
        return apiResponse;
    }

    public ApiResponse fallback(final Throwable t) {
        log.info("Fallback cause, {}", t.toString());
        return ApiResponse.builder().build();
    }

}
