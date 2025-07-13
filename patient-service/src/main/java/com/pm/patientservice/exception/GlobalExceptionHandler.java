package com.pm.patientservice.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach((
                error -> errors.put(error.getField(), error.getDefaultMessage())));

        return ResponseEntity.badRequest().body(errors);

    }


    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String,String>> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {

        // logger for developer
        // exception for client
        log.warn("Email address already exists {}",ex.getMessage());
        Map<String , String> errors = new HashMap<>();
        errors.put("message" , "Email address already exists");
        return ResponseEntity.badRequest().body(errors);

    }

    @ExceptionHandler(InvalidDateFormatException.class)
    public ResponseEntity<Map<String,String>> handleInvalidDateFormatException(InvalidDateFormatException ex) {
        log.warn("Invalid date format {}",ex.getMessage());

        String responseMessage = switch (ex.getFieldName()) {
            case "dateOfBirth" -> "The date of birth format is invalid. Please use yyyy-MM-dd";
            case "registrationDate" -> "The date of registration date is invalid. Please use yyyy-MM-dd";
            default -> ex.getMessage();
        };
        Map<String , String> errors = new HashMap<>();
        errors.put("message" ,responseMessage);
        return ResponseEntity.badRequest().body(errors);
    }


    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<Map<String,String>> handlePatientNotFoundException(PatientNotFoundException ex) {
        log.warn("Patient not found {}",ex.getMessage());
        Map<String,String> errors = new HashMap<>();
        errors.put("message" , "Patient not found");
        return ResponseEntity.badRequest().body(errors);
    }
}
