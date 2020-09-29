package com.trl.springbootspringsecurityangularjwt.enumeration;

import static com.trl.springbootspringsecurityangularjwt.enumeration.Authority.*;

public enum Role {

    ROLE_USER(USER_AUTHORITIES.getValue()),
    ROLE_HR(HR_AUTHORITIES.getValue()),
    ROLE_MANAGER(MANAGER_AUTHORITIES.getValue()),
    ROLE_ADMIN(ADMIN_AUTHORITIES.getValue()),
    ROLE_SUPER_ADMIN(SUPER_ADMIN_AUTHORITIES.getValue());

    private final String[] authorities;

    Role(String[] authorities) {
        this.authorities = authorities;
    }

    public String[] getAuthorities() {
        return this.authorities;
    }
}
