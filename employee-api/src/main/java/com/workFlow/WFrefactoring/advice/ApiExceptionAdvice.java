package com.workFlow.WFrefactoring.advice;

import com.workFlow.WFrefactoring.exception.ApiException;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Profile("dev")
public class ApiExceptionAdvice {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<String> handleApiException(ApiException e){
        return ResponseEntity.status(e.getStatus()).body(e.getMessage());
    }

}
