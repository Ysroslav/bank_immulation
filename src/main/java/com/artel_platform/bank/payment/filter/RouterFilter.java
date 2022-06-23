package com.artel_platform.bank.payment.filter;

import com.artel_platform.bank.payment.exception.IllegalListHeadersInRequestException;
import com.artel_platform.bank.payment.utils.UtilException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFilterFunction;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class RouterFilter implements HandlerFilterFunction<ServerResponse, ServerResponse> {

    private final UtilException utilException;

    private static final String KEY_HEADER_ERROR = "Headers don't contain idempotence keys";

    @Override
    public Mono<ServerResponse> filter(
            final ServerRequest request,
            final HandlerFunction<ServerResponse> handlerFunction
    ) {
        final var headers = request.headers().asHttpHeaders();
        if (!headers.containsKey("Idempotence-Key")) {
            return utilException.getAttributesError(
                    new IllegalListHeadersInRequestException(KEY_HEADER_ERROR),
                    request);
        }
        return handlerFunction.handle(request);
    }

}
