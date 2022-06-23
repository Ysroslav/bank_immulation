package com.artel_platform.bank.payment.dto;

import com.artel_platform.bank.payment.enums.Currency;
import com.fasterxml.jackson.annotation.JsonProperty;

public record AmountDTO(
    @JsonProperty("value") String value,
    @JsonProperty("currency") Currency currency
) {
}
