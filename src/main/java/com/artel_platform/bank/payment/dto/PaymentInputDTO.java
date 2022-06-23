package com.artel_platform.bank.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PaymentInputDTO(
        @JsonProperty("amount") AmountDTO amount,
        @JsonProperty("capture") boolean capture,
        @JsonProperty("confirmation") ConfirmationInputDTO confirmation,
        @JsonProperty("description") String description
) {
}
