package com.example.bookstoreproject.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends DomainException{

    public UnauthorizedException() {
        super(HttpStatus.UNAUTHORIZED, "");
    }
}
