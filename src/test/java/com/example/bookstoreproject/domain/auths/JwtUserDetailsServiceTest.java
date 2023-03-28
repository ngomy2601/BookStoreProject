package com.example.bookstoreproject.domain.auths;

import com.example.bookstoreproject.error.NotFoundException;
import com.example.bookstoreproject.persistence.role.RoleStore;
import com.example.bookstoreproject.persistence.user.UserStore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static com.example.bookstoreproject.fakes.RoleFakes.buildRole;
import static com.example.bookstoreproject.fakes.UserFakes.buildUser;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class JwtUserDetailsServiceTest {

    @Mock
    private UserStore userStore;

    @Mock
    private RoleStore roleStore;
    @InjectMocks
    private JwtUserDetailsService jwtUserDetailsService;

    @Test
    void loadUserByUsername() {
        final var username = randomAlphabetic(3, 10);
        when(userStore.findByUsername(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> jwtUserDetailsService.loadUserByUsername(username));
    }

    @Test
    void loadUserByUsername_WhenUsernameIsNotFound_OK() {
        final var user = buildUser();
        final var role = buildRole();

        role.setId(user.getRoleId());
        when(userStore.findByUsername(any())).thenReturn(Optional.of(user));

        when(roleStore.findById(role.getId())).thenReturn(Optional.of(role));

        final UserDetails actual = jwtUserDetailsService.loadUserByUsername(user.getUsername());

        assertEquals(user.getUsername(), actual.getUsername());
        verify(userStore).findByUsername(anyString());
        verify(roleStore).findById(role.getId());
    }
}