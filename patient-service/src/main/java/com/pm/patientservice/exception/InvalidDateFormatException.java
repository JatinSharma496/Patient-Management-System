package com.pm.patientservice.exception;

public class InvalidDateFormatException extends RuntimeException {

    private final String fieldName;
    public InvalidDateFormatException(String fieldName, String value) {
        super("Invalid date format for field " + fieldName + ": " + value);
        this.fieldName = fieldName;
    }
    public String getFieldName() {
        return fieldName;
    }

}