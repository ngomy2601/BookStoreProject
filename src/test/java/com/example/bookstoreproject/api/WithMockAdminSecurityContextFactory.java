package com.example.bookstoreproject.api;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import static com.example.bookstoreproject.fakes.UserAuthenticationTokenFakes.buildAdmin;

public class WithMockAdminSecurityContextFactory implements WithSecurityContextFactory<WithMockAdmin> {
    @Override
    public SecurityContext createSecurityContext(WithMockAdmin annotation) {
        final var context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(buildAdmin());
        return context;
    }
}
