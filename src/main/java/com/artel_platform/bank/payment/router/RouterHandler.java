package com.artel_platform.bank.payment.router;

import com.artel_platform.bank.payment.filter.RouterFilter;
import com.artel_platform.bank.payment.handler.PaymentHandler;
import com.artel_platform.bank.payment.property.CommonProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@RequiredArgsConstructor
public class RouterHandler {

    private final CommonProperty property;
    private final RouterFilter filter;

    @Bean
    public RouterFunction<ServerResponse> route(final PaymentHandler handler) {
        return RouterFunctions.route()
                .GET(property.getBasePath() + "/authorize", handler::authorize)
                .POST(property.getBasePath() + "/payment", handler::createPayment)
                .GET(property.getBasePath() + "/test", handler::testAuth)
                .GET(property.getBasePath() + "/payments/{payment_id}", handler::checkStatus)
                .filter(filter)
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> routePublic(final PaymentHandler handler) {
        return RouterFunctions.route()
                              .POST(property.getBaseIndex() + "/add", handler::addPayment)
                              .GET(property.getBaseIndex() + "/index", handler::index)
                              .build();
    }

}
