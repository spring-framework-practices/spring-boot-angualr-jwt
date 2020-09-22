package com.trl.springbootspringsecurityangularjwt.security;

public interface SecurityConstant {

    long EXPIRATION_TIME = 432_000_000; // 5 days expressed in milliseconds.
    String TOKEN_PREFIX = "Bearer ";
    String JWT_TOKEN_HEADER = "Jwt-Token";
    String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";
    String GET_ARRAYS_LLC = "Get Arrays, LLC";
    String GET_ARRAYS_ADMINISTRATION = "User Management Portal";
    String AUTHORITIES = "authorities";
    String FORBIDDEN_MESSAGE = "You need to log in to access this page";
    String ACCESS_DENIED_MESSAGE = "You do not have permission to access this page";
    String OPTIONS_HTTP_METHOD = "OPTIONS";
    String[] PUBLIC_URLS = {"/user/login", "/user/register", "/user/resetpassword/**", "/user/image/**"};
}
