package com.artel_platform.bank.payment.dto;

import com.artel_platform.bank.payment.enums.ConfirmationType;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ConfirmationInputDTO(
        @JsonProperty("type") ConfirmationType confirmation,
        @JsonProperty("return_url") String returnUrl
) {

    public ConfirmationType getType(){
        return confirmation;
    }

    public String getReturn_url(){
        return returnUrl;
    }
}
