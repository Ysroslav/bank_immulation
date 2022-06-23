package com.artel_platform.bank.payment.exception;

import java.io.Serial;

public class NotFoundPaymentIdExcepion extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 6349874748485443796L;

    public NotFoundPaymentIdExcepion(String s){
        super(s);
    }
}
