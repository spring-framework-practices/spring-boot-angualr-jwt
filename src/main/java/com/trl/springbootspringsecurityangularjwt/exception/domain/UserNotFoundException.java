package com.trl.springbootspringsecurityangularjwt.exception.domain;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message) {
        super(message);
    }
}
