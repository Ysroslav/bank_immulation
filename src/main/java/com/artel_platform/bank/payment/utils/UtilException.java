package com.artel_platform.bank.payment.utils;

import com.artel_platform.bank.payment.exception.IllegalListHeadersInRequestException;
import com.artel_platform.bank.payment.exception.ValidationRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@Component
@Slf4j
public class UtilException {

    public Mono<ServerResponse> getAttributesError(
            Throwable serverException,
            ServerRequest request
    ) {
        log.error("Error! {} message: {}", serverException, serverException.getMessage());
        final var map = new HashMap<String, Object>();
        map.put("status", getStatusWithException(serverException));
        map.put("error", serverException.toString());
        map.put("message", serverException.getMessage());
        map.put("timestamp", ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
        map.put("path", request.path());

        return ServerResponse.status(getStatusWithException(serverException))
                             .contentType(MediaType.APPLICATION_JSON)
                             .body(BodyInserters.fromValue(map));

    }

    private HttpStatus getStatusWithException(
            final Throwable serverException
    ) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        if (serverException instanceof IllegalListHeadersInRequestException) {
            status = HttpStatus.BAD_REQUEST;
        }

        if (serverException instanceof ValidationRequestException) {
            status = HttpStatus.BAD_REQUEST;
        }

        if (serverException instanceof RuntimeException) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return status;
    }

    public static <T > void validateObject(T target, Validator validator){
        final Errors errors = new BeanPropertyBindingResult(
                target,
                target.getClass().getName());
        validator.validate(target, errors);
        if (!errors.getAllErrors().isEmpty()){
            throw new ValidationRequestException("Payment not validate");
        }
    }
}
