package com.trl.springbootspringsecurityangularjwt.constant;

public class UserImplConstant {

    public static final String USERNAME_ALREADY_EXISTS = "Username: '%s' already exists";
    public static final String EMAIL_ALREADY_EXISTS = "Email: '%s' already exists";
    public static final String NO_USER_FOUND_BY_USERNAME = "No user found by username: '%s'";
    public static final String NO_USERS_FOUND = "No users found.";
    public static final String FOUND_USER_BY_USERNAME = "Returning found user by username: ";
    public static final String NO_USER_FOUND_BY_EMAIL = "No user found for email: %s";
    public static final String ILLEGAL_ARGUMENTS = "One or more of the passed arguments is null or empty";
    public static final String ILLEGAL_ARGUMENT = "Argument is null or empty";
    public static final String EMAIL_ADDRESS_INVALID = "Email address: '%s' is invalid";

    private UserImplConstant() {
    }
}
