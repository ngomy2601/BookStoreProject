package com.example.bookstoreproject.api;

import com.example.bookstoreproject.domain.auths.UserAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.List;
import java.util.UUID;

public class WithMockUserSecurityContextFactory implements WithSecurityContextFactory<WithMockUser> {

    @Override
    public SecurityContext createSecurityContext(WithMockUser annotation) {
        final var context = SecurityContextHolder.createEmptyContext();
        final Authentication auth = new UserAuthenticationToken(UUID.fromString("d222d1e5-ab31-441b-be90-e74ce7cd32dd"), "user", List.of(new SimpleGrantedAuthority("ROLE_USER")));
        context.setAuthentication(auth);
        return context;
    }
}
