package com.suunya.subscription.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(MobileNumberValidationException.class)
    public ResponseEntity<ApiError> apiErrorResponseEntity(MobileNumberValidationException e)
    {
        ApiError apiError = ApiError.builder().message(e.getMessage())
                .code(HttpStatus.NOT_ACCEPTABLE.toString()).build();
        return new ResponseEntity<>(apiError,HttpStatus.NOT_ACCEPTABLE);

    }
}