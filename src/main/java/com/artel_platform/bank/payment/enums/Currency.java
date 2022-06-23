package com.artel_platform.bank.payment.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Currency {

    RUB("RUB"),
    EUR("EUR"),
    USD("USD");

    final String currency;

    Currency(final String currency) {
        this.currency = currency;
    }

    @JsonValue
    public String getCurrency(){
        return currency;
    }
}
