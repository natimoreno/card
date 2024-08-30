package com.bank.card.service;

import com.bank.card.repository.Card;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Service
public class CallService {

    public final RestClient restClient;

    private static final String URI = "http://localhost:8080/bank/card/%s";


    public CallService(RestClient restClient) {

        this.restClient = restClient;
    }
    public Optional<Card> retrieveCard(Long id) {

        String uri = String.format(URI, id);

        return Optional.ofNullable(restClient.get()
                .uri(uri)
                .retrieve()
                .body(Card.class));
    }
}
