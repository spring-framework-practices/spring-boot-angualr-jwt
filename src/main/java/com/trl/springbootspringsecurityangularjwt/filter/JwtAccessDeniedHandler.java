package com.trl.springbootspringsecurityangularjwt.filter;

import com.trl.springbootspringsecurityangularjwt.exception.ExceptionDetails;

import static com.trl.springbootspringsecurityangularjwt.constant.SecurityConstant.ACCESS_DENIED_MESSAGE;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException, ServletException {
        ExceptionDetails responseExceptionDetails = new ExceptionDetails.Builder()
                .withHttpStatusCode(UNAUTHORIZED.value())
                .withHttpStatus(UNAUTHORIZED)
                .withReason(UNAUTHORIZED.getReasonPhrase().toUpperCase())
                .withMessage(ACCESS_DENIED_MESSAGE)
                .build();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(UNAUTHORIZED.value());
        OutputStream outputStream = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(outputStream, responseExceptionDetails);
        outputStream.flush();
    }
}
