package com.trl.springbootspringsecurityangularjwt.exception.handler;

import com.trl.springbootspringsecurityangularjwt.exception.ExceptionDetails;
import com.trl.springbootspringsecurityangularjwt.exception.domain.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
@RestController
public class UserExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserExceptionHandler.class);

    @ExceptionHandler(EmailExistException.class)
    public ResponseEntity<ExceptionDetails> emailExistException(EmailExistException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(UsernameExistException.class)
    public ResponseEntity<ExceptionDetails> usernameExistException(UsernameExistException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<ExceptionDetails> emailNotFoundException(EmailNotFoundException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionDetails> userNotFoundException(UserNotFoundException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(NotAnImageFileException.class)
    public ResponseEntity<ExceptionDetails> notAnImageFileException(NotAnImageFileException exception) {
        LOGGER.error(exception.getMessage());
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
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
