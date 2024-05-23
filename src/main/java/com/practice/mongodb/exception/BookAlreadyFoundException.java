package com.practice.mongodb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BookAlreadyFoundException extends  RuntimeException{

    public BookAlreadyFoundException(String message) {
        super(message);
    }
}
