package com.bank.card.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class TestService {

    public final RestClient restClient;

    public TestService(RestClient restClient) {

        this.restClient = restClient;
    }

    public String get() {
        return restClient.get()
                .uri("https://postman-echo.com/get")
                .retrieve()
                .body(String.class);
    }
}
