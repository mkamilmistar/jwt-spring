package com.jwt.userservice.configuration.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwt.userservice.common.model.ErrorResponse;
import com.jwt.userservice.util.Constant;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@Component
public class CustomAuthenticationEntry implements AuthenticationEntryPoint {


    private static final String DEFAULT_CODE = "80000";


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.error("Token Error, {}", authException.getMessage());

        ErrorResponse errorResponse = new ErrorResponse();

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            Exception exception = (Exception) request.getAttribute("exception");

            String message;

            if (exception != null) {

                if (exception.getCause() != null) {
                    message = exception.getCause().toString() + " " + exception.getMessage();
                } else {
                    message = exception.getMessage();
                }

                errorResponse.setCode("80003");
                errorResponse.setTitle(Constant.ERR_TITLE_INVALID_NEXT_STEP);
                errorResponse.setMessage(message);
                response.getOutputStream()
                        .println(new ObjectMapper().writeValueAsString(errorResponse));

            } else {

                if (authException.getCause() != null) {
                    message = authException.getCause().toString() + " " + authException.getMessage();
                } else {
                    message = authException.getMessage();
                }

                errorResponse.setCode(DEFAULT_CODE);
                errorResponse.setTitle(Constant.ERR_TITLE_INVALID_NEXT_STEP);
                errorResponse.setMessage(message);
                response.getOutputStream()
                        .println(new ObjectMapper().writeValueAsString(errorResponse));
            }
        }

}
