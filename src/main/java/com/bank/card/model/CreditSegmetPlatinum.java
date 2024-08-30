package com.bank.card.model;

import com.bank.card.model.constants.Currency;
import org.springframework.stereotype.Component;

@Component
public class CreditSegmetPlatinum extends CreditSegment {

    private final static String PLATINUM = "PLATINUM";

    private final static Double MIN_VALUE = 2000D;

    private final static Currency ACTIVE_CURRENCY = Currency.PESOS;

    @Override
    public boolean isValid(PayloadDTO payloadDTO) {
        return payloadDTO.getAmount() >= MIN_VALUE && ACTIVE_CURRENCY.equals(payloadDTO.getCurrency());
    }

    public String getName() {
        return PLATINUM;
    }
}
