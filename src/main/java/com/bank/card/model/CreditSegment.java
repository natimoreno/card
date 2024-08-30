package com.bank.card.model;

import org.springframework.stereotype.Component;

@Component
public abstract class CreditSegment {

    public abstract boolean isValid(PayloadDTO payloadDTO);

    public abstract String getName();
}
