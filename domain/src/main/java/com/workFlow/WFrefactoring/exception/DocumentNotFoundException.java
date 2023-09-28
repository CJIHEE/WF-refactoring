package com.workFlow.WFrefactoring.exception;

import org.springframework.http.HttpStatus;

public class DocumentNotFoundException extends ApiException{
    public DocumentNotFoundException(String msg) {
        super(msg);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
