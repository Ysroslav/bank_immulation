package com.artel_platform.bank.payment.exception;

import java.io.Serial;

public class IllegalListHeadersInRequestException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 2748167718529695815L;

    public IllegalListHeadersInRequestException(String s){
        super(s);
    }
}
