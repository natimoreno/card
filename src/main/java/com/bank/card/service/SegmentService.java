package com.bank.card.service;

import com.bank.card.ApiExceptions.ApiException;
import com.bank.card.model.CreditSegment;
import com.bank.card.model.PayloadDTO;
import com.bank.card.model.constants.Status;
import com.bank.card.repository.Card;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.javapoet.ClassName;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Logger;

@Data
@Service
public class SegmentService {

    @Autowired
    public List<CreditSegment> creditSegmentList;

    private CardService cardService;

    private static final Logger LOGGER = Logger.getLogger(ClassName.class.getName());

    private final static int SEGMENT_VALID = 1;


    public SegmentService(List<CreditSegment> creditSegmentList, CardService cardService) {

        this.creditSegmentList = creditSegmentList;
        this.cardService = cardService;
    }

    public Optional<Card> create(PayloadDTO payloadDTO) throws ApiException {

        payloadDTO.checkValue();
        String type = retrieveSegment(payloadDTO);
        Card card = createCard(type, payloadDTO);
        return cardService.store(card);
    }

    private Card createCard(String type, PayloadDTO payloadDTO) {

        Card card = new Card();
        card.setName(payloadDTO.getUser().getName().toUpperCase(Locale.ROOT));
        card.setStatus(Status.CREATED.name());
        card.setSegment(type);
        return card;
    }

    private String retrieveSegment(PayloadDTO payloadDTO) {

        List<String> list = creditSegmentList.stream()
                .filter(segment -> segment.isValid(payloadDTO))
                .map(CreditSegment::getName)
                .toList();

        if (list.size() == SEGMENT_VALID) {
            return Optional.ofNullable(list.getFirst()).get();
        }
        throw new InternalError("Error at segment.");
    }
}
