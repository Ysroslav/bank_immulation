package com.artel_platform.bank.payment.service;

import com.artel_platform.bank.payment.dto.ConfirmationDTO;
import com.artel_platform.bank.payment.dto.MetadataDTO;
import com.artel_platform.bank.payment.dto.PaymentDTO;
import com.artel_platform.bank.payment.dto.PaymentInputDTO;
import com.artel_platform.bank.payment.dto.PaymentMetadata;
import com.artel_platform.bank.payment.dto.RecipientDTO;
import com.artel_platform.bank.payment.enums.StatusPayment;
import com.artel_platform.bank.payment.property.CommonProperty;
import com.artel_platform.bank.payment.utils.MapPayment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    static DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;

    private final CommonProperty commonProperty;
    private final MapPayment mapPayment;

    public PaymentDTO createPayment(final PaymentInputDTO input){

        final var id = UUID.randomUUID().toString();

        final var payment =  new PaymentDTO(
                id,
                StatusPayment.PENDING,
                false,
                input.amount(),
                new ConfirmationDTO(
                    input.confirmation().confirmation(), commonProperty.getPaymentUrl() + "?payment_id=" + id
                ),
                ZonedDateTime.now().format(formatter),
                input.description(),
                new MetadataDTO(),
                new RecipientDTO(
                        "100500", "100700"
                ),
                false,
                false
        );

        final var paymentMetadata = new PaymentMetadata();
        paymentMetadata.setPayment(payment);
        paymentMetadata.setReturnUrl(input.confirmation().returnUrl());

        mapPayment.add(paymentMetadata);
        return payment;
    }
}
