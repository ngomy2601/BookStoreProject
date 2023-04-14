package com.example.bookstoreproject.domain.auths;

import com.example.bookstoreproject.domain.user.User;
import com.example.bookstoreproject.persistence.role.RoleStore;
import com.example.bookstoreproject.persistence.user.UserStore;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

import static java.util.Collections.singleton;

@Service
@RequiredArgsConstructor
public class GoogleLoginService {
    private final GoogleTokenVerifierService googleTokenVerifierService;

    private final UserStore userStore;

    private final RoleStore roleStore;

    public UserDetails loginGoogle(final String decodedToken) {
        final GoogleTokenPayload googleAccount = googleTokenVerifierService.googleIdTokenVerifier(decodedToken);
        return userStore.findByUsername(googleAccount.getEmail())
                .map(this::getJwtUserDetails)
                .orElseGet(() -> createNewGoogleUser(googleAccount));
    }

    private JwtUserDetails getJwtUserDetails(final User user) {
        final String roleName = roleStore.findRoleName(user.getRoleId());
        return new JwtUserDetails(user.getId(), user.getUsername(), user.getPassword(), createAuthorities(roleName));
    }

    private JwtUserDetails createNewGoogleUser(final GoogleTokenPayload googleAccount) {
        final UUID roleId = roleStore.findIdByName("CONTRIBUTOR");
        final User newUser = createUser(googleAccount, roleId);
        return new JwtUserDetails(newUser.getId(), newUser.getUsername(), newUser.getPassword(), createAuthorities("CONTRIBUTOR"));
    }

    private User createUser(final GoogleTokenPayload googleAccount, UUID roleId) {
        final User newUser = User.builder()
                .username(googleAccount.getEmail())
                .password(UUID.randomUUID().toString())
                .roleId(roleId)
                .build();
        return userStore.create(newUser);
    }

    private Collection<? extends GrantedAuthority> createAuthorities(final String role) {
        return singleton(new SimpleGrantedAuthority(role));
    }
}
