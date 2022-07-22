package com.artel_platform.bank.payment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentMetadata {

    private PaymentDTO payment;
    private String returnUrl;
}
