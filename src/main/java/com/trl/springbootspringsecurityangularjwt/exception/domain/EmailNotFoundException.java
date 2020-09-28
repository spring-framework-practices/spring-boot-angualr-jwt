package com.trl.springbootspringsecurityangularjwt.exception.domain;

public class EmailNotFoundException extends RuntimeException {

    public EmailNotFoundException(String message) {
        super(message);
    }
}
