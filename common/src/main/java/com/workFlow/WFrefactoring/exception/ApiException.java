package com.workFlow.WFrefactoring.exception;

import org.springframework.http.HttpStatus;

public abstract class ApiException extends RuntimeException{
    public abstract HttpStatus getStatus();

    public ApiException(String msg){super(msg);};

}
