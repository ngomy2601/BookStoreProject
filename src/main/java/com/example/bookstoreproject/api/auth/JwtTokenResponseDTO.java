package com.example.bookstoreproject.api.auth;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class JwtTokenResponseDTO {

    private final String token;
}
