package com.trl.springbootspringsecurityangularjwt.user;

public enum UserAuthority {

    ADMIN("ROLE_ADMIN");

    private final String value;

    UserAuthority(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
