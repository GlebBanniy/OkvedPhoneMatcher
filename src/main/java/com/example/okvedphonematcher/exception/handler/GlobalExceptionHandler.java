package com.example.okvedphonematcher.exception.handler;

import com.example.okvedphonematcher.controller.dto.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@Order(1000)
@RestControllerAdvice
@RequiredArgsConstructor
class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorResponse handle(Throwable exception) {
        log.error("Error: {}", exception.getMessage(), exception);
        return ErrorResponse.builder()
          .code(INTERNAL_SERVER_ERROR.value())
          .description(exception.getMessage())
          .build();
    }
}
