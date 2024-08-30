package com.bank.card.service;

import com.bank.card.ApiExceptions.ApiException;
import com.bank.card.model.constants.Status;
import com.bank.card.repository.Card;
import com.bank.card.repository.CardRepository;
import org.springframework.javapoet.ClassName;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CardService {

    private final CardRepository cardRepository;

    private static final Logger LOGGER = Logger.getLogger(ClassName.class.getName());


    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Optional<Card> store(Card card) {

        cardRepository.save(card);
        LOGGER.info(String.format("[Card Service] CREATED card ID %d", card.getId()));
        return Optional.of(card);
    }

    public Optional<Card> update(Long id, Card newCard) throws ApiException {

        Optional<Card> card = cardRepository.findById(id);
        if (card.isPresent()) {
            Card copyCard = modifyCard(card.get(), newCard);
            cardRepository.save(copyCard);
            LOGGER.info(String.format("[Card Service] UPDATED card ID %d", id));
            return Optional.of(copyCard);
        } else {
            throw new ApiException(String.format("[Card Service] Failed update with card ID %d", id));
        }
    }

    private Card modifyCard(Card tmpCard, Card newCard) throws ApiException {

        if (!isValidStatus(newCard.getStatus())) {
            throw new ApiException(String.format("[Card Service] Noy valid status: %s", newCard.getStatus()));
        }

        tmpCard.setName(tmpCard.getName());
        tmpCard.setStatus(newCard.getStatus());
        return tmpCard;
    }

    private boolean isValidStatus(String newStatus) {
        return Arrays.stream(Status.values()).anyMatch(values -> values.name().equals(newStatus));
    }

    public Optional<Card> retrieve(Long id) {
        return cardRepository.findById(id);
    }

    public void delete(Long id) {
        LOGGER.info(String.format("[Card Service] DELETED card ID %d", id));
        cardRepository.deleteById(id);
    }
}
