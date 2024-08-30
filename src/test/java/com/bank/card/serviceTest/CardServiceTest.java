package com.bank.card.serviceTest;

import com.bank.card.ApiExceptions.ApiException;
import com.bank.card.repository.Card;
import com.bank.card.repository.CardRepository;
import com.bank.card.service.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardService cardService;

    private Card card;

    private static final String USER_NAME = "USER_NAME";

    private static final String STATUS_ACTIVE = "STATUS_ACTIVE";

    private static final String STATUS_INACTIVE = "INACTIVE";

    private static final String SEGMENT_CLASSIC = "SEGMENT_CLASSIC";

    @BeforeEach
    void setup() {
        card = new Card();
        card.setId(1);
        card.setName(USER_NAME);
        card.setStatus(STATUS_ACTIVE);
        card.setSegment(SEGMENT_CLASSIC);
    }

    @Test
    void testStoreCard() {

        when(cardRepository.save(any(Card.class))).thenReturn(card);
        Optional<Card> result = cardService.store(card);

        verify(cardRepository).save(card);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId());
        assertEquals(USER_NAME, result.get().getName());
        assertEquals(STATUS_ACTIVE, result.get().getStatus());
        assertEquals(SEGMENT_CLASSIC, result.get().getSegment());
    }

    @Test
    void testRetrieveCard() {


        when(this.cardRepository.findById(anyLong())).thenReturn(Optional.of(card));
        Optional<Card> result = this.cardService.retrieve(anyLong());

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId());
        assertEquals(USER_NAME, result.get().getName());
        assertEquals(STATUS_ACTIVE, result.get().getStatus());
        assertEquals(SEGMENT_CLASSIC, result.get().getSegment());
    }

    @Test
    void testUpdateCard() throws ApiException {

        card.setStatus(STATUS_INACTIVE);

        when(cardRepository.findById(1L)).thenReturn(Optional.ofNullable(card));
        when(cardRepository.save(any(Card.class))).thenReturn(card);
        Optional<Card> result = this.cardService.update(1L, card);

        verify(cardRepository).save(card);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId());
        assertEquals(USER_NAME, result.get().getName());
        assertEquals(STATUS_INACTIVE, result.get().getStatus());
        assertEquals(SEGMENT_CLASSIC, result.get().getSegment());
    }

    @Test
    void testUpdateCardInvalidStatus() {

        when(cardRepository.findById(1L)).thenReturn(Optional.ofNullable(card));

        card.setStatus("STATUS_INACTIVE");
        assertThrows(ApiException.class, () -> this.cardService.update(1L, card));
    }

}
