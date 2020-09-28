package com.trl.springbootspringsecurityangularjwt.exception.domain;

public class EmailIsInvalidException extends RuntimeException {

    public EmailIsInvalidException(String message) {
        super(message);
    }
}
