package com.example.bookstoreproject.error;

import org.springframework.http.HttpStatus;

public class BadRequestException extends DomainException{
    public BadRequestException(final String message, Object... args){
        super(HttpStatus.NOT_FOUND, message, args);
    }
}
