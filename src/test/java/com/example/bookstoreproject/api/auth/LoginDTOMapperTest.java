package com.example.bookstoreproject.api.auth;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginDTOMapperTest {

    @Test
    void toAuthentication() {
        LoginDTO loginDTO = LoginDTO.builder()
                .username(randomAlphabetic(3, 10))
                .password(randomAlphabetic(3,10))
                .build();

        Authentication authentication = LoginDTOMapper.toAuthentication(loginDTO);
        assertEquals(loginDTO.getUsername(), authentication.getPrincipal());
        assertEquals(loginDTO.getPassword(), authentication.getCredentials());
    }
}