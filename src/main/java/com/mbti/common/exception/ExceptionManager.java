package com.mbti.common.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.mbti.presentation.dto.Response;

@RestControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(UserJoinLoginException.class)
    public ResponseEntity<?>userJoinLoginExceptionHandler(UserJoinLoginException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatus()).body(Response.error(e.getErrorCode().name()));

    }

}
