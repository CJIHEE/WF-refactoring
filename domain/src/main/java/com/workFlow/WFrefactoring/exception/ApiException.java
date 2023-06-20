package com.workFlow.WFrefactoring.exception;

import org.springframework.http.HttpStatus;

public abstract class ApiException extends RuntimeException {
    //Http 상태코드 가져오기
    public abstract HttpStatus getStatus();
    //RuntimeException 기본 생성자 호출
    public ApiException(String msg) {
        super(msg);
    }
}
