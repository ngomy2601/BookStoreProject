package com.example.bookstoreproject.domain.auths;

import com.example.bookstoreproject.error.UnauthorizedException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.example.bookstoreproject.fakes.UserAuthenticationTokenFakes.buildAdmin;
import static com.example.bookstoreproject.fakes.UserAuthenticationTokenFakes.buildContributor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
class AuthsProviderTest {
    @InjectMocks
    private AuthsProvider authsProvider;

    @Test
    void getCurrentAuthentication_Contributor() {
        final var user = buildContributor();

        SecurityContextHolder.getContext().setAuthentication(user);

        final var actual = authsProvider.getCurrentAuthentication();

        assertEquals(user, actual);
    }

    @Test
    void getCurrentAuthentication_ThrowException() {
        SecurityContextHolder.getContext().setAuthentication(null);

        assertThrows(UnauthorizedException.class, () -> authsProvider.getCurrentAuthentication());
    }

    @Test
    void getCurrentAuthentication_Admin() {
        final var user = buildAdmin();

        SecurityContextHolder.getContext().setAuthentication(user);

        final var actual = authsProvider.getCurrentAuthentication();

        assertEquals(user, actual);
    }

    @Test
    void getCurrentUserRole_Contributor() {
        final var user = buildContributor();

        SecurityContextHolder.getContext().setAuthentication(user);

        final var actual = authsProvider.getCurrentUserRole();

        assertEquals(user.getRole(), actual);
    }

    @Test
    void getCurrentUserRole_Admin() {
        final var user = buildAdmin();

        SecurityContextHolder.getContext().setAuthentication(user);

        final var actual = authsProvider.getCurrentUserRole();

        assertEquals(user.getRole(), actual);
    }

    @Test
    void getCurrentUserId_Contributor() {
        final var user = buildContributor();

        SecurityContextHolder.getContext().setAuthentication(user);

        final var actual = authsProvider.getCurrentUserId();

        assertEquals(user.getUserId(), actual);
    }

    @Test
    void getCurrentUserId_Admin() {
        final var user = buildAdmin();

        SecurityContextHolder.getContext().setAuthentication(user);

        final var actual = authsProvider.getCurrentUserId();

        assertEquals(user.getUserId(), actual);
    }
}