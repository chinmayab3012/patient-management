package com.pm.doctorservice.exception;

public class PhoneAlreadyExistException extends RuntimeException{
    private final String field;
    public PhoneAlreadyExistException(String message,String field){
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
