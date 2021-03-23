package com.interview.carwash.controller;

import com.interview.carwash.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler()
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        ErrorDto errorDto = new ErrorDto(ex.getMessage());
        return handleExceptionInternal(ex, errorDto,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
