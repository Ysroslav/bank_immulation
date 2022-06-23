package com.artel_platform.bank.payment.dto;

import com.artel_platform.bank.payment.enums.StatusPayment;
import com.fasterxml.jackson.annotation.JsonProperty;

public record PaymentDTO(
        @JsonProperty("id") String id,
        @JsonProperty("status") StatusPayment statusPayment,
        @JsonProperty("paid") boolean paid,
        @JsonProperty("amount") AmountDTO amount,
        @JsonProperty("confirmation") ConfirmationDTO confirmation,
        @JsonProperty("created_at") String createdAt,
        @JsonProperty("description") String description,
        @JsonProperty("metadata") MetadataDTO metadata,
        @JsonProperty("recipient") RecipientDTO recipient,
        @JsonProperty("refundable") boolean refundable,
        @JsonProperty("test") boolean test
) {

    public PaymentDTO changeStatus(StatusPayment status){
        return new PaymentDTO(
                id,
                status,
                paid,
                amount,
                confirmation,
                createdAt,
                description,
                metadata,
                recipient,
                refundable,
                test
        );
    }
}
