package com.workFlow.WFrefactoring.exception;

import org.springframework.http.HttpStatus;

public class checkAutenticationException extends ApiException{
    public checkAutenticationException(String msg) {
        super(msg);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
