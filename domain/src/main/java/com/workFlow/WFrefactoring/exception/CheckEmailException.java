package com.workFlow.WFrefactoring.exception;

import org.springframework.http.HttpStatus;

public class CheckEmailException extends ApiException {

    public CheckEmailException(String msg) {
        super(msg);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
