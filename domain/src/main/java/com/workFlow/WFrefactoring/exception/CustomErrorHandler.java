package com.workFlow.WFrefactoring.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

//@Controller나 @RestController에서 발생한 예외를 한 곳에서 관리하고 처리할 수 있게 도와주는 어노테이션
@RestController
public class CustomErrorHandler {
    //@Controller, @RestController가 적용된 Bean내에서 발생하는 예외를 잡아서 하나의 메서드에서 처리해주는 기능을 한다.
    //Controller계층에서 발생하는 에러를 잡아서 메서드로 처리해주는 기능이라 Service, Repository에서 발생하는 에러는 제외한다.
    @ExceptionHandler(ApiException.class) //ApiException.class에 맵핑하여 특정 exception을 잡도록
    public ResponseEntity handlerError(ApiException e) {
        return  new ResponseEntity(e.getStatus()); // 상태코드 리턴
    }

}
