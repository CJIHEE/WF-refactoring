package com.workFlow.WFrefactoring.exception;

import org.springframework.http.HttpStatus;

public class DeptNotFoundException extends ApiException {

    public DeptNotFoundException(String msg) {
        super(msg);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
