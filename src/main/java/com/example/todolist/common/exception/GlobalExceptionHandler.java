package com.example.todolist.common.exception;

import com.example.todolist.common.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    protected ResponseEntity<?> handleCustomException(CustomException ex){
        return new ResponseEntity<>(
                new ResponseMessage<>(ex.getErrorCode().getMsg(), ex.getErrorCode().getStatusCode(), ex.getErrorCode()), HttpStatus.OK
        );
    }
}
