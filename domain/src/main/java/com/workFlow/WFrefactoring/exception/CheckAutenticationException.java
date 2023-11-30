package com.workFlow.WFrefactoring.exception;

import org.springframework.http.HttpStatus;

public class CheckAutenticationException extends ApiException{
    public CheckAutenticationException(String msg) {
        super(msg);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}
