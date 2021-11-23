package com.jwt.userservice.configuration.security.exception;

import com.jwt.userservice.common.model.ErrorResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Log4j2
@ControllerAdvice
public class TokenGlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> accessDeniedExceptionResponse(AccessDeniedException exception) {
        ErrorResponse errorResponse = new ErrorResponse("80004", "", "Role not authorize");
        log.error("Role unauthorize, {}", exception.getMessage());

        return new ResponseEntity<>(errorResponse, new HttpHeaders(),
                HttpStatus.FORBIDDEN);
    }

}

