package com.artel_platform.bank.payment.property;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class CommonProperty {

    @Value("${basePath.api}")
    private String basePath;

    @Value("${setting.client}")
    private String client;

    @Value("${setting.secret}")
    private String secret;

    @Value("${path.payment}")
    private String paymentUrl;
}
