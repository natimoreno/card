package com.bank.card.modelTest;

import com.bank.card.ApiExceptions.ApiException;
import com.bank.card.model.PayloadDTO;
import com.bank.card.model.User;
import com.bank.card.model.constants.Currency;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PayloadDTOTest {

    @Test
    void testCheckValueThrowsExceptionForNegativeAmount() {
        PayloadDTO payload = new PayloadDTO();
        payload.setAmount(-1.0);

        assertThrows(ApiException.class, payload::checkValue, "Amount has a negative number.");
    }

    @Test
    void testCheckValueAmount() {

        PayloadDTO payload = new PayloadDTO();
        payload.setAmount(1000D);

        assertEquals(payload.getAmount(), 1000D);
    }

    @Test
    void testCheckValueCurrencyDolar() {

        PayloadDTO payload = new PayloadDTO();
        payload.setCurrency(Currency.DOLAR);

        assertEquals(Currency.DOLAR, payload.getCurrency());
    }

    @Test
    void testCheckValueCurrencyPesos() {

        PayloadDTO payload = new PayloadDTO();
        payload.setCurrency(Currency.PESOS);

        assertEquals(Currency.PESOS, payload.getCurrency());
    }

    @Test
    void testCheckValueUser() {

        User user = new User("1", "NAME");
        PayloadDTO payload = new PayloadDTO();
        payload.setUser(user);

        assertEquals(user.getId(), "1");
        assertEquals(user.getName(), "NAME");
    }
}
