package com.example.bookstoreproject.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends DomainException{

    public NotFoundException(final String message, Object... args){
        super(HttpStatus.NOT_FOUND, message, args);
    }
}
