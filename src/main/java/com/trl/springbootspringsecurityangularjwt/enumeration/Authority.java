package com.trl.springbootspringsecurityangularjwt.enumeration;

public enum  Authority {

    USER_AUTHORITIES(new String[]{"user:read"}),
    HR_AUTHORITIES(new String[]{"user:read", "user:update"}),
    MANAGER_AUTHORITIES(new String[]{"user:read", "user:update"}),
    ADMIN_AUTHORITIES(new String[]{"user:read", "user:create", "user:update"}),
    SUPER_ADMIN_AUTHORITIES(new String[]{"user:read", "user:create", "user:update", "user:delete"});

    private final String[] value;

    Authority(String[] value) {
        this.value = value;
    }

    public String[] getValue() {
        return this.value;
    }
}
