package com.guilhermeramos31.springbootjuniorcase.exception;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
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
    private ResponseEntity<ErrorResponse> buildErrorResponse(String exceptionMessage, HttpStatus status, String defaultMsg) {
        var message = (exceptionMessage != null && !exceptionMessage.isBlank()) ? exceptionMessage : defaultMsg;

        var error = new ErrorResponse();
        error.setMessage(message);
        error.setStatus(status.value());
        error.setCode(status.name());
        error.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        var fieldError = exception.getFieldError();
        var defaultMessage = "Validation error";
        var message = (fieldError != null && fieldError.getDefaultMessage() != null)
                ? fieldError.getDefaultMessage()
                : defaultMessage;

        return buildErrorResponse(message, HttpStatus.BAD_REQUEST, defaultMessage);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ErrorResponse> handlerEntityNotFoundException(EntityNotFoundException exception) {
        return buildErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND, "Entity not found");
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> handlerIllegalArgumentException(IllegalArgumentException exception) {
        return buildErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST, "Invalid request");
    }

    @ExceptionHandler({EntityExistsException.class})
    public ResponseEntity<ErrorResponse> handlerEntityExistsException(EntityExistsException exception) {
        return buildErrorResponse(exception.getMessage(), HttpStatus.CONFLICT, "Entity already exists");
    }

    @ExceptionHandler({Throwable.class})
    public ResponseEntity<ErrorResponse> handlerException(Throwable exception) {
        return buildErrorResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
    }
}
