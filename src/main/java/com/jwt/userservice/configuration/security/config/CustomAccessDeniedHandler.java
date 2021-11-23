package com.jwt.userservice.configuration.security.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwt.userservice.common.model.ErrorResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Log4j2
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private static final String DEFAULT_CODE = "80004";
    private static final String DEFAULT_TITLE = "Access denied";
    private static final String DEFAULT_MESSAGE = "Access denied";

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {

        log.error("Access denied, {}", e.getMessage());

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(DEFAULT_CODE);
        errorResponse.setTitle(DEFAULT_TITLE);
        errorResponse.setMessage(DEFAULT_MESSAGE);

        httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON.toString());
        httpServletResponse.getOutputStream()
                .println(new ObjectMapper().writeValueAsString(errorResponse));
    }


}
