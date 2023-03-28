package com.example.bookstoreproject.domain.auths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.example.bookstoreproject.fakes.UserAuthenticationTokenFakes.buildAdmin;
import static com.example.bookstoreproject.fakes.UserAuthenticationTokenFakes.buildContributor;
import static org.junit.jupiter.api.Assertions.assertEquals;

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