package com.trl.springbootspringsecurityangularjwt.filter;

import com.trl.springbootspringsecurityangularjwt.exception.ExceptionDetails;

import static com.trl.springbootspringsecurityangularjwt.constant.SecurityConstant.FORBIDDEN_MESSAGE;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.FORBIDDEN;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class JwtAuthenticationEntryPoint extends Http403ForbiddenEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response, AuthenticationException exception) throws IOException {
        ExceptionDetails responseExceptionDetails = new ExceptionDetails.Builder()
                .withHttpStatusCode(FORBIDDEN.value())
                .withHttpStatus(FORBIDDEN)
                .withReason(FORBIDDEN.getReasonPhrase().toUpperCase())
                .withMessage(FORBIDDEN_MESSAGE)
                .build();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(FORBIDDEN.value());
        OutputStream outputStream = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(outputStream, responseExceptionDetails);
        outputStream.flush();
    }
}
