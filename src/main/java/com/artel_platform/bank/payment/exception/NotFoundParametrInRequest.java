package com.artel_platform.bank.payment.exception;

import java.io.Serial;

public class NotFoundParametrInRequest extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 690139688660344845L;

    public NotFoundParametrInRequest(String s){
        super(s);
    }
}
