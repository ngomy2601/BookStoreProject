package com.example.bookstoreproject.api;

import com.example.bookstoreproject.error.DomainException;
import com.example.bookstoreproject.error.ErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

public class ControllerErrorHandling extends ResponseEntityExceptionHandler {
    @ExceptionHandler({DomainException.class})
    public ResponseEntity<ErrorDTO> handleDomainException(final DomainException error) {
        final var errorDTO = ErrorDTO.builder()
                .message(error.getMessage())
                .occurAt(Instant.now())
                .build();

        return ResponseEntity
                .status(error.getHttpStatus())
                .body(errorDTO);
    }
}
