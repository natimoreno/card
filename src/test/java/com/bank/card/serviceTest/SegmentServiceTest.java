package com.bank.card.serviceTest;

import com.bank.card.ApiExceptions.ApiException;
import com.bank.card.model.*;
import com.bank.card.model.constants.Currency;
import com.bank.card.repository.Card;
import com.bank.card.repository.CardRepository;
import com.bank.card.service.CardService;
import com.bank.card.service.SegmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class SegmentServiceTest {

    private List<CreditSegment> creditSegmentList;

    @Mock
    private CardService cardService = new CardService(mock(CardRepository.class));

    @InjectMocks
    private SegmentService segmentService;

    private Card card;

    private PayloadDTO payloadDTO;

    private static final String USER_NAME = "USER_NAME";

    private static final String STATUS_ACTIVE = "ACTIVE";

    private static final String SEGMENT_CLASSIC = "CLASSIC";

    private static final String SEGMENT_PLATINUM = "PLATINUM";

    private static final String SEGMENT_GOL= "GOL";


    @Test
    void testStoreCardClassic() throws ApiException {
        card = new Card();
        card.setId(1);
        card.setName(USER_NAME);
        card.setStatus(STATUS_ACTIVE);
        card.setSegment(SEGMENT_CLASSIC);

        payloadDTO = new PayloadDTO();
        payloadDTO.setUser(new User("1", USER_NAME));
        payloadDTO.setAmount(1000D);
        payloadDTO.setCurrency(Currency.PESOS);

        creditSegmentList = List.of(new CreditSegmetClassic());
        segmentService.setCreditSegmentList(creditSegmentList);

        when(cardService.store(any(Card.class))).thenReturn(Optional.ofNullable(card));
        Optional<Card> result = segmentService.create(payloadDTO);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId());
        assertEquals(USER_NAME, result.get().getName());
        assertEquals(STATUS_ACTIVE, result.get().getStatus());
        assertEquals(SEGMENT_CLASSIC, result.get().getSegment());
    }

    @Test
    void testStoreCardPlatinum() throws ApiException {
        card = new Card();
        card.setId(1);
        card.setName(USER_NAME);
        card.setStatus(STATUS_ACTIVE);
        card.setSegment(SEGMENT_CLASSIC);

        payloadDTO = new PayloadDTO();
        payloadDTO.setUser(new User("1", USER_NAME));
        payloadDTO.setAmount(2500D);
        payloadDTO.setCurrency(Currency.PESOS);

        creditSegmentList = List.of(new CreditSegmetPlatinum());
        segmentService.setCreditSegmentList(creditSegmentList);

        when(cardService.store(any(Card.class))).thenReturn(Optional.ofNullable(card));
        Optional<Card> result = segmentService.create(payloadDTO);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId());
        assertEquals(USER_NAME, result.get().getName());
        assertEquals(STATUS_ACTIVE, result.get().getStatus());
        assertEquals(SEGMENT_PLATINUM, result.get().getSegment());
    }

    @Test
    void testStoreCardGol() throws ApiException {

        card = new Card();
        card.setId(1);
        card.setName(USER_NAME);
        card.setStatus(STATUS_ACTIVE);
        card.setSegment(SEGMENT_CLASSIC);

        payloadDTO = new PayloadDTO();
        payloadDTO.setUser(new User("1", USER_NAME));
        payloadDTO.setAmount(3500D);
        payloadDTO.setCurrency(Currency.DOLAR);

        creditSegmentList = List.of(new CreditSegmetGold());
        segmentService.setCreditSegmentList(creditSegmentList);

        when(cardService.store(any(Card.class))).thenReturn(Optional.ofNullable(card));
        Optional<Card> result = segmentService.create(payloadDTO);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId());
        assertEquals(USER_NAME, result.get().getName());
        assertEquals(STATUS_ACTIVE, result.get().getStatus());
        assertEquals(SEGMENT_GOL, result.get().getSegment());
    }

    @Test
    void testStoreCardError() throws ApiException {

        card = new Card();
        card.setId(1);
        card.setName(USER_NAME);
        card.setStatus(STATUS_ACTIVE);
        card.setSegment(SEGMENT_CLASSIC);

        payloadDTO = new PayloadDTO();
        payloadDTO.setUser(new User("1", USER_NAME));
        payloadDTO.setAmount(3500D);
        payloadDTO.setCurrency(Currency.DOLAR);

        creditSegmentList = List.of(new CreditSegmetPlatinum());
        segmentService.setCreditSegmentList(creditSegmentList);

        assertThrows(InternalError.class, () -> this.segmentService.create(payloadDTO));
    }
}
