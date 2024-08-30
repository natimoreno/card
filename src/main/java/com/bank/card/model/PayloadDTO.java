package com.bank.card.model;

import com.bank.card.ApiExceptions.ApiException;
import com.bank.card.model.constants.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayloadDTO {

    private User user;

    private Currency currency;

    private Double amount;


    public void checkValue() throws ApiException {
        if (this.amount < 0) {
            throw new ApiException("Amount has a negative number.");
        }
    }
}
