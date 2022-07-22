package com.artel_platform.bank.payment.handler;

import com.artel_platform.bank.payment.dto.PaymentInputDTO;
import com.artel_platform.bank.payment.enums.StatusPayment;
import com.artel_platform.bank.payment.exception.NotFoundParametrInRequest;
import com.artel_platform.bank.payment.service.PaymentService;
import com.artel_platform.bank.payment.utils.MapPayment;
import com.artel_platform.bank.payment.utils.UtilException;
import com.artel_platform.bank.payment.validation.AmountValidation;
import com.artel_platform.bank.payment.validation.ConfirmationValidation;
import com.artel_platform.bank.payment.validation.PaymentValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentHandler {

    private final PaymentService paymentService;
    private final UtilException utilException;
    private final MapPayment mapPayment;

    public Mono<ServerResponse> index(final ServerRequest request) {
        log.info("start redirect to index");
        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_HTML)
                .render("index");
    }

    public <T extends ServerResponse> Mono<T> authorize(final ServerRequest request) {
        return null;
    }

    public Mono<ServerResponse> createPayment(final ServerRequest request) {
        return request
                .bodyToMono(PaymentInputDTO.class)
                .map(this::validateInput)
                .map(paymentService::createPayment)
                .flatMap(a -> ServerResponse.ok().bodyValue(a))
                .onErrorResume(e -> utilException.getAttributesError(e, request));
    }

    private PaymentInputDTO validateInput(final PaymentInputDTO payment){
        UtilException.validateObject(payment, new PaymentValidation());
        UtilException.validateObject(payment.amount(), new AmountValidation());
        UtilException.validateObject(payment.confirmation(), new ConfirmationValidation());
        return payment;
    }


    public Mono<ServerResponse> testAuth(final ServerRequest request) {
        return ServerResponse.ok().build();
    }

    public Mono<ServerResponse> checkStatus(final ServerRequest request) {
        log.info("start method checkStatus");
        final var idRate = request.pathVariable("payment_id");
        if (idRate.isBlank()){
            throw new NotFoundParametrInRequest("payment_id not found");
        }
        log.info(idRate);
        return Mono.just(mapPayment.get(idRate))
            .flatMap(a -> ServerResponse.ok().bodyValue(a.getPayment()))
            .onErrorResume(e -> utilException.getAttributesError(e, request));
    }

    public Mono<ServerResponse> addPayment(ServerRequest request) {
        log.info("start method addPayment");
        final var idPayment = request.queryParam("payment_id")
                                     .orElseThrow(() -> new NotFoundParametrInRequest("payment_id not found"));

        return Mono.just(mapPayment.get(idPayment))
            .doOnNext(paymentMeta -> {
                var payment = paymentMeta.getPayment();
                payment = payment.changeStatus(StatusPayment.SUCCEEDED);
                paymentMeta.setPayment(payment);
            })
            .flatMap(a -> ServerResponse.status(HttpStatus.OK).bodyValue(a.getReturnUrl()))
            .onErrorResume(e -> utilException.getAttributesError(e, request));
    }
}
