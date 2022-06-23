package com.artel_platform.bank.payment.enums;

public enum StatusPayment {

    PENDING("pending"),
    SUCCEEDED("succeeded"),
    CANCELED("canceled");

    StatusPayment(final String status){}
}
