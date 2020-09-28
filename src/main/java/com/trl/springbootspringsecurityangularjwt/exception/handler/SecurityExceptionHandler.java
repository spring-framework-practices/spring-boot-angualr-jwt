package com.trl.springbootspringsecurityangularjwt.exception.handler;

import com.trl.springbootspringsecurityangularjwt.exception.ExceptionDetails;

import com.auth0.jwt.exceptions.TokenExpiredException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class SecurityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityExceptionHandler.class);

    private static final String ACCOUNT_LOCKED = "Your account has been locked. Please contact administration";
    private static final String INCORRECT_CREDENTIALS = "Username / password incorrect. Please try again";
    private static final String ACCOUNT_DISABLED = "Your account has been disabled. If this is an error, please contact administration";
    private static final String NOT_ENOUGH_PERMISSION = "You do not have enough permission";

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ExceptionDetails> accountDisabledException() {
        return createHttpResponse(BAD_REQUEST, ACCOUNT_DISABLED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionDetails> badCredentialsException() {
        return createHttpResponse(BAD_REQUEST, INCORRECT_CREDENTIALS);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionDetails> accessDeniedException() {
        return createHttpResponse(FORBIDDEN, NOT_ENOUGH_PERMISSION);
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<ExceptionDetails> lockedException() {
        return createHttpResponse(UNAUTHORIZED, ACCOUNT_LOCKED);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<ExceptionDetails> tokenExpiredException(TokenExpiredException exception) {
        return createHttpResponse(UNAUTHORIZED, exception.getMessage());
    }

    private ResponseEntity<ExceptionDetails> createHttpResponse(HttpStatus httpStatus, String message) {
        ExceptionDetails exceptionDetails = new ExceptionDetails.Builder()
                .withHttpStatusCode(httpStatus.value())
                .withHttpStatus(httpStatus)
                .withReason(httpStatus.getReasonPhrase().toUpperCase())
                .withMessage(message)
                .build();
        return new ResponseEntity<>(exceptionDetails, httpStatus);
    }
}
