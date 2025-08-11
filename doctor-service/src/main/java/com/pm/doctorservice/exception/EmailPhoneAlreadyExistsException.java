package com.pm.doctorservice.exception;

public class EmailPhoneAlreadyExistsException extends  RuntimeException{

    private final String field;
    public EmailPhoneAlreadyExistsException(String message,String field){
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
