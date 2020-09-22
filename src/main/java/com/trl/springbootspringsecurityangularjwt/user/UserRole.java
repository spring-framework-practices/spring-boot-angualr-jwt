package com.trl.springbootspringsecurityangularjwt.user;

public enum UserRole {

    ADMIN("ROLE_ADMIN");

    private final String value;

    UserRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
