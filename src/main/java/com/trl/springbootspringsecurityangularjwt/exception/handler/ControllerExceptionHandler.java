package com.trl.springbootspringsecurityangularjwt.exception.handler;

import com.trl.springbootspringsecurityangularjwt.exception.ExceptionDetails;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.net.http.HttpResponse;

@RestControllerAdvice
public class ControllerExceptionHandler implements ErrorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    public static final String ERROR_PATH = "/error";

//    @ExceptionHandler(NoHandlerFoundException.class)
//    public ResponseEntity<HttpResponse> noHandlerFoundException(NoHandlerFoundException e) {
//        return createHttpResponse(BAD_REQUEST, "There is no mapping for this URL");
//    }

    @RequestMapping(ERROR_PATH)
    public ResponseEntity<ExceptionDetails> notFound404() {
        return createHttpResponse(NOT_FOUND, "There is no mapping for this URL");
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
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
