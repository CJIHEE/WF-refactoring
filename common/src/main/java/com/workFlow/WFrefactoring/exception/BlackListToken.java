package com.workFlow.WFrefactoring.exception;

import org.springframework.http.HttpStatus;

public class BlackListToken extends ApiException{

    public BlackListToken(String msg) {
        super(msg);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
