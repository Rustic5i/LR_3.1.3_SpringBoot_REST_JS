package com.baratov.spring.springboot.myExcetion;

public class NoSuchUserException extends  RuntimeException{
    public NoSuchUserException(String message) {
        super(message);
    }
}
