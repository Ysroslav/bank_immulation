package com.artel_platform.bank.payment.utils;

import com.artel_platform.bank.payment.dto.PaymentDTO;
import com.artel_platform.bank.payment.exception.NotFoundPaymentIdExcepion;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MapPayment {

    private final Map<String, PaymentDTO> mapPayment = new HashMap<>();

    public void add(PaymentDTO payment){
        mapPayment.put(payment.id(), payment);
    }

    public PaymentDTO get(String id){
        if (!mapPayment.containsKey(id)) {
            throw new NotFoundPaymentIdExcepion(id + " not found");
        }
        return mapPayment.get(id);
    }

}
