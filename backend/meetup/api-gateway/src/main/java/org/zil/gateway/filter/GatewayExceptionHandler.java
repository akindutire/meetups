package org.zil.gateway.filter;

import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GatewayExceptionHandler {

    //    @ExceptionHandler(value = Exception.class)
//    public Mono<ServerResponse> handleIllegalState(ServerWebExchange exchange, Exception exc) {
//        exchange.getAttributes().putIfAbsent(ErrorAttributes.ERROR_ATTRIBUTE, exc);
//        return ServerResponse.from(ErrorResponse.builder(exc, HttpStatus.FORBIDDEN,exc.getMessage()).build());
//    }

}
