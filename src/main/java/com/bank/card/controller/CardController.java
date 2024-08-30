package com.bank.card.controller;

import com.bank.card.ApiExceptions.ApiException;
import com.bank.card.repository.Card;
import com.bank.card.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/bank")
public class CardController {

    CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/card/{id}")
    public ResponseEntity<Card> retrieveCard(@PathVariable Long id) {

        Optional<Card> card = cardService.retrieve(id);
        return card.map(c -> ResponseEntity.ok().body(c)).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/card/{id}")
    public ResponseEntity<Optional<Card>> updateCard(@PathVariable Long id, @RequestBody Card card) throws ApiException {

        Optional<Card> updatedCard = cardService.update(id, card);
        return ResponseEntity.ok().body(updatedCard);
    }

    @PostMapping("/card")
    public ResponseEntity<Card> createCard(@RequestBody Card card) {

        Optional<Card> createdCard = cardService.store(card);
        return createdCard.map(c -> ResponseEntity.ok().body(c)).orElse(ResponseEntity.noContent().build());
    }

    @DeleteMapping("/card/{id}")
    public ResponseEntity<Card> deleteCard(@PathVariable Long id) {

        cardService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/v2/card/{id}")
    public ResponseEntity<Card> searchCard(@PathVariable Long id) {

        return this.retrieveCard(id);
    }
}
