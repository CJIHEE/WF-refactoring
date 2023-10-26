package com.workFlow.WFrefactoring.exception;

import org.springframework.http.HttpRange;
import org.springframework.http.HttpStatus;

public class ApprovalNotFoundException extends ApiException{
    public ApprovalNotFoundException(String msg) {
        super(msg);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
