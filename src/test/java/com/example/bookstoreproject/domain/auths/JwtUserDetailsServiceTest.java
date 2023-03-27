package com.example.bookstoreproject.domain.auths;

import com.example.bookstoreproject.error.NotFoundException;
import com.example.bookstoreproject.persistence.role.RoleStore;
import com.example.bookstoreproject.persistence.user.UserStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class JwtUserDetailsServiceTest {

    @Mock
    private UserStore userStore;

    @Mock
    private RoleStore roleStore;
    @InjectMocks
    private JwtUserDetailsService jwtUserDetailsService;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        jwtUserDetailsService = new JwtUserDetailsService(userStore, roleStore);
    }
    @Test
    void loadUserByUsername() {
        final var username = randomAlphabetic(3, 10);
        when(userStore.findByUsername(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> jwtUserDetailsService.loadUserByUsername(username));
    }
}