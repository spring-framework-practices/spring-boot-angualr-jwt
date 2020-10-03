package com.trl.springbootspringsecurityangularjwt.exception.domain;

public class UsersNotFoundException extends RuntimeException{

    public UsersNotFoundException(String message) {
        super(message);
    }
}
