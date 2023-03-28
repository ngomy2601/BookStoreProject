package com.example.bookstoreproject.domain.auths;

import com.example.bookstoreproject.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.Date;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class JwtTokenServiceTest {

    private static final String SECRET = "testSecret";
    private static final Long EXPIRATION = 3600L;
    private JwtTokenService jwtTokenService;
    @Mock
    private JwtProperties jwtProperties;

    @BeforeEach
    void setUp() {
        openMocks(this);
        jwtTokenService = new JwtTokenService(jwtProperties);
    }
    @Test
    void generateToken_OK() {
        final JwtUserDetails jwtUserDetails = new JwtUserDetails(null, "user", randomAlphabetic(3, 10), Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
        when(jwtProperties.getSecret()).thenReturn(SECRET);
        when(jwtProperties.getExpiration()).thenReturn(EXPIRATION);

        final String token = jwtTokenService.generateToken(jwtUserDetails);
        final Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        final Date expiration = claims.getExpiration();
        final Date issuedAt = claims.getIssuedAt();

        assertEquals("user", claims.getSubject());
        assertEquals("ROLE_USER", claims.get("roles").toString());
        assertTrue(expiration.toInstant().isAfter(issuedAt.toInstant()));
        assertEquals(expiration.getTime() - issuedAt.getTime(), EXPIRATION * 1000);
    }
}