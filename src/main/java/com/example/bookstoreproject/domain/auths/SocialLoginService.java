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
public class SocialLoginService {
    private final GoogleTokenVerifierService googleTokenVerifierService;

    private final FacebookTokenVerifierService facebookTokenVerifierService;

    private final UserStore userStore;

    private final RoleStore roleStore;

    public UserDetails loginGoogle(final String decodedToken) {
        final SocialTokenPayload googleAccount = googleTokenVerifierService.googleIdTokenVerifier(decodedToken);
        return userStore.findByUsername(googleAccount.getEmail())
                .map(this::getJwtUserDetails)
                .orElseGet(() -> createNewSocialUser(googleAccount, "google"));
    }

    public UserDetails loginFacebook(final String decodeToken) {
        final var facebookAccount = facebookTokenVerifierService.verifyFacebookToken(decodeToken);
        return userStore.findByUsername(facebookAccount.getUsername())
                .map(this::getJwtUserDetails)
                .orElseGet(() -> createNewSocialUser(facebookAccount, "facebook"));
    }

    private JwtUserDetails getJwtUserDetails(final User user) {
        final String roleName = roleStore.findRoleName(user.getRoleId());
        return new JwtUserDetails(user.getId(), user.getUsername(), user.getPassword(), createAuthorities(roleName));
    }


    private JwtUserDetails createNewSocialUser(final SocialTokenPayload socialAccount, final String typeAccount) {
        final UUID roleId = roleStore.findIdByName("CONTRIBUTOR");
        final User newUser = createUser(socialAccount, roleId, typeAccount);
        return new JwtUserDetails(newUser.getId(), newUser.getUsername(), newUser.getPassword(), createAuthorities("CONTRIBUTOR"));
    }

    private User createUser(final SocialTokenPayload socialAccount, final UUID roleId, final String typeAccount) {
        final User newUser = User.builder()
                .password(UUID.randomUUID().toString())
                .firstName(socialAccount.getFirstName())
                .lastName(socialAccount.getLastName())
                .roleId(roleId)
                .build();

        if (typeAccount.equals("google")) {
            newUser.setUsername(socialAccount.getEmail());
        } else if (typeAccount.equals("facebook")) {
            newUser.setUsername(socialAccount.getUsername());
        }
        return userStore.create(newUser);
    }

    private Collection<? extends GrantedAuthority> createAuthorities(final String role) {
        return singleton(new SimpleGrantedAuthority(role));
    }
}
