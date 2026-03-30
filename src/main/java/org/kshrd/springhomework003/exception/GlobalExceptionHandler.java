package org.kshrd.springhomework003.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.validation.method.ParameterValidationResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.net.URI;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundExceptionHandler.class)
    public ProblemDetail handleNotFoundExceptionHandler(NotFoundExceptionHandler ex){
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                ex.getMessage());
        detail.setProperty("type", URI.create("http://localhost:8080/errors/not-found"));
        detail.setProperty("timestamp", Instant.now());
        return detail;
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        HashMap<String , String> errors = new HashMap<>();

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Invalid Fields");
        detail.setProperty("timestamp", Instant.now());
        detail.setProperty("errors", errors);
        return detail;
    }
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ProblemDetail handleHandlerMethodValidationException(HandlerMethodValidationException ex) {
        ProblemDetail detail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        Map<String, Object> errors = new HashMap<>();

        for (ParameterValidationResult result : ex.getParameterValidationResults()) {
            String parameter = result.getMethodParameter().getParameterName();

            for (var errorMessage : result.getResolvableErrors()) {
                errors.put(parameter, errorMessage.getDefaultMessage());
            }

        }

        detail.setProperty("errors", errors);

        return detail;
    }
}
