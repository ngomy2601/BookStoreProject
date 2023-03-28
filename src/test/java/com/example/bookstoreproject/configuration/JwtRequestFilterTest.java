package com.example.bookstoreproject.configuration;

import com.example.bookstoreproject.domain.auths.JwtTokenService;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class JwtRequestFilterTest {

    @Mock
    private JwtTokenService jwtTokenService;

    @Mock
    private FilterChain filterChain;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private JwtRequestFilter jwtRequestFilter;
    @Test
    void doFilterInternal() throws ServletException, IOException {
        final String token = randomAlphabetic(3, 10);
        final String authorizationHeader = "Bearer" + token;

        when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn(authorizationHeader);
        when(jwtTokenService.parse(token)).thenReturn(authentication);

        jwtRequestFilter.doFilterInternal(request, response, filterChain);

        final var header = request.getHeader(HttpHeaders.AUTHORIZATION);

        assertEquals(authorizationHeader, header);

        verify(jwtTokenService).parse(token);
    }
}