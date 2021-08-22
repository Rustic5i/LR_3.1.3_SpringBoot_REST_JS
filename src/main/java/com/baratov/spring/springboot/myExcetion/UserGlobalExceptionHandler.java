package com.baratov.spring.springboot.myExcetion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserGlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<UserInCorrectData> handlerException(NoSuchUserException exception){
        UserInCorrectData userInCorrectData = new UserInCorrectData();
        userInCorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<>(userInCorrectData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<UserInCorrectData> handlerException(Exception exception){
        UserInCorrectData userInCorrectData = new UserInCorrectData();
        userInCorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<>(userInCorrectData, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<UserInCorrectData> handlerException(SaveObjectException exception){
        UserInCorrectData data = new UserInCorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data,HttpStatus.NOT_FOUND);
    }
}
