package com.guilhermeramos31.springbootjuniorcase.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handlerArgumentNotValidException(MethodArgumentNotValidException exception) {
        var error = new ErrorResponse();
        var httpStatus = HttpStatus.BAD_REQUEST;

        var fieldError = exception.getFieldError();
        var message = (fieldError != null && fieldError.getDefaultMessage() != null)
                ? fieldError.getDefaultMessage()
                : "Erro de validação genérico";

        error.setMessage(message);
        error.setStatus(httpStatus.value());
        error.setCode(httpStatus.name());
        error.setTimestamp(LocalDateTime.now());

        return ResponseEntity
                .status(httpStatus)
                .body(error);
    }
}
