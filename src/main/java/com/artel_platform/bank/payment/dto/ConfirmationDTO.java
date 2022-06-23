package com.artel_platform.bank.payment.dto;

import com.artel_platform.bank.payment.enums.ConfirmationType;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ConfirmationDTO(
        @JsonProperty("type") ConfirmationType confirmation,
        @JsonProperty("confirmation_url") String confirmationUrl
) {
}
