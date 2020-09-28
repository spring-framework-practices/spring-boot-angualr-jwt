package com.trl.springbootspringsecurityangularjwt.exception.domain;

public class UsernameNotFoundException extends RuntimeException {

    public UsernameNotFoundException(String message) {
        super(message);
    }
}
