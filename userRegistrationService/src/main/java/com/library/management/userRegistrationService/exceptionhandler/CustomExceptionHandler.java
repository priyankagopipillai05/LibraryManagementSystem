package com.library.management.userRegistrationService.exceptionhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{


@Override
public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                           HttpHeaders headers,
                                                           HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        //Get all errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);
        body.put("applicationId",headers.get("applicationType"));

        return new ResponseEntity<>(body, headers, status);

    }

    /*@ExceptionHandler(ConstraintViolationException.class)
    public void constraintViolationException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }*/

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleConstraintViolationException(
            Exception ex,
            WebRequest request) {

        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());

        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Constraint Violations" ,
                details);

        return new ResponseEntity<ApiError>(err,HttpStatus.BAD_REQUEST);
    }
}
