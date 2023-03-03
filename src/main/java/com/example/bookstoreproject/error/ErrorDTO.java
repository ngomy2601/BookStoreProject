package com.example.bookstoreproject.error;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class ErrorDTO {
    private String message;
    private Instant occurAt;
}
