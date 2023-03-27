package com.example.bookstoreproject.domain.auths;

import com.example.bookstoreproject.error.UnauthorizedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthsProvider {
    public UserAuthenticationToken getCurrentAuthentication() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new UnauthorizedException();
        }

        return (UserAuthenticationToken) authentication;
    }

    public String getCurrentUserRole() {
        return getCurrentAuthentication().getRole();
    }

    public UUID getCurrentUserId() {
        return getCurrentAuthentication().getUserId();
    }
}
