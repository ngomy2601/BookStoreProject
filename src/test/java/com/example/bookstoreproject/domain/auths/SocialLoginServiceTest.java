package com.example.bookstoreproject.domain.auths;

import com.example.bookstoreproject.domain.user.User;
import com.example.bookstoreproject.persistence.role.RoleStore;
import com.example.bookstoreproject.persistence.user.UserStore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.bookstoreproject.fakes.GoogleTokenPayloadFakes.buildToken;
import static com.example.bookstoreproject.fakes.JwtUserDetailFakes.buildJwtUserDetails;
import static com.example.bookstoreproject.fakes.UserFakes.buildUser;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SocialLoginServiceTest {

    @Mock
    private UserStore userStore;

    @Mock
    private GoogleTokenVerifierService googleTokenVerifierService;

    @Mock
    private FacebookTokenVerifierService facebookTokenVerifierService;

    @Mock
    private RoleStore roleStore;

    @InjectMocks
    private SocialLoginService socialLoginService;


    @Test
    void shouldLoginGoogle_OK() {
        final SocialTokenPayload tokenPayload = buildToken();
        final var user = buildUser();
        final var authorities = randomAlphabetic(3,10);

        user.setUsername(tokenPayload.getEmail());

        final JwtUserDetails userDetails = buildJwtUserDetails();

        when(googleTokenVerifierService.googleIdTokenVerifier(anyString())).thenReturn(tokenPayload);
        when(userStore.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
        when(roleStore.findRoleName(user.getRoleId())).thenReturn(authorities);

        final var actual = socialLoginService.loginGoogle(anyString());

        assertEquals(userDetails, actual);


        verify(googleTokenVerifierService).googleIdTokenVerifier(anyString());
        verify(userStore).findByUsername(user.getUsername());
        verify(roleStore).findRoleName(user.getRoleId());
    }

    @Test
    void shouldLoginGoogle_CreateNewUser() {
        final SocialTokenPayload tokenPayload = buildToken();

        when(googleTokenVerifierService.googleIdTokenVerifier(anyString())).thenReturn(tokenPayload);
        when(userStore.findByUsername(tokenPayload.getEmail())).thenReturn(Optional.empty());

        final var uid = UUID.randomUUID();

        when(roleStore.findIdByName(anyString())).thenReturn(uid);

        final User newUser = User.builder()
                .username(tokenPayload.getEmail())
                .password(UUID.randomUUID().toString())
                .firstName(tokenPayload.getFirstName())
                .lastName(tokenPayload.getLastName())
                .avatar(tokenPayload.getAvatar())
                .roleId(uid)
                .build();

        when(userStore.create(any())).thenReturn(newUser);

        final JwtUserDetails userDetails = buildJwtUserDetails();


        final var actual = socialLoginService.loginGoogle(anyString());
        assertEquals(userDetails, actual);

        verify(googleTokenVerifierService).googleIdTokenVerifier(anyString());
        verify(userStore).findByUsername(tokenPayload.getEmail());
        verify(roleStore).findIdByName(anyString());
        verify(userStore).create(any());
    }
    @Test
    void shouldLoginFacebook_OK() {
        final SocialTokenPayload tokenPayload = buildToken();
        final var user = buildUser();
        final var token = randomAlphabetic(3, 10);
        final var authorities = randomAlphabetic(3, 10);

        user.setUsername(tokenPayload.getUsername());

        final JwtUserDetails userDetails = new JwtUserDetails(user.getId(), user.getUsername(), user.getPassword(), List.of(new SimpleGrantedAuthority(authorities)));

        when(facebookTokenVerifierService.verifyFacebookToken(token)).thenReturn(tokenPayload);
        when(userStore.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
        when(roleStore.findRoleName(user.getRoleId())).thenReturn(authorities);

        final var actual = socialLoginService.loginFacebook(token);

        assertEquals(userDetails, actual);

        verify(facebookTokenVerifierService).verifyFacebookToken(token);
        verify(userStore).findByUsername(user.getUsername());
        verify(roleStore).findRoleName(user.getRoleId());
    }
}