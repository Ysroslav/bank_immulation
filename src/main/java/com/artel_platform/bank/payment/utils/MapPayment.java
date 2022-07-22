package com.artel_platform.bank.payment.utils;

import com.artel_platform.bank.payment.dto.PaymentDTO;
import com.artel_platform.bank.payment.dto.PaymentMetadata;
import com.artel_platform.bank.payment.exception.NotFoundPaymentIdExcepion;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MapPayment {

    private final Map<String, PaymentMetadata> mapPayment = new HashMap<>();

    public void add(PaymentMetadata payment){
        mapPayment.put(payment.getPayment().id(), payment);
    }

    public PaymentMetadata get(String id){
        if (!mapPayment.containsKey(id)) {
            throw new NotFoundPaymentIdExcepion(id + " not found");
        }
        return mapPayment.get(id);
    }

}
