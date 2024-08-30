package com.bank.card.model;

import com.bank.card.model.constants.Currency;
import org.springframework.stereotype.Component;

@Component
public class CreditSegmetGold extends CreditSegment {

    private final static String GOLD = "GOLD";

    private final static Double MIN_VALUE = 3000D;

    private final static Currency ACTIVE_CURRENCY = Currency.DOLAR;

    @Override
    public boolean isValid(PayloadDTO payloadDTO) {
        return payloadDTO.getAmount() >= MIN_VALUE && ACTIVE_CURRENCY.equals(payloadDTO.getCurrency());
    }

    public String getName() {
        return GOLD;
    }
}
