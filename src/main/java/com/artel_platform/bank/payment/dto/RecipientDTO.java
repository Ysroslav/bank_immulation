package com.artel_platform.bank.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RecipientDTO(
        @JsonProperty("account_id") String accountId,
        @JsonProperty("gateway_id") String gatewayId
) {
}
