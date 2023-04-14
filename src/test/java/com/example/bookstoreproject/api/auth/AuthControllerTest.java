package com.example.bookstoreproject.api.auth;

import com.example.bookstoreproject.api.AbstractControllerTest;
import com.example.bookstoreproject.domain.auths.GoogleLoginService;
import com.example.bookstoreproject.domain.auths.JwtTokenService;
import com.example.bookstoreproject.domain.auths.JwtUserDetails;
import com.example.bookstoreproject.domain.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import static com.example.bookstoreproject.fakes.AuthFakes.buildAuthentication;
import static com.example.bookstoreproject.fakes.JwtUserDetailFakes.buildJwtUserDetails;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class AuthControllerTest extends AbstractControllerTest {

    private static final String BASE_URL = "/api/v1/auth";

    @MockBean
    private JwtTokenService jwtTokenService;

    @MockBean
    private UserService userService;

    private GoogleLoginService googleLoginService;

    @MockBean
    private AuthenticationManager authenticationManager;

    @Test
    void shouldLogin_OK() throws Exception {
        final var auth = buildAuthentication();
        final var token = randomAlphabetic(3, 15);

        when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(auth);

        when(jwtTokenService.generateToken((JwtUserDetails) auth.getPrincipal()))
                .thenReturn(token);

        post(BASE_URL, auth).andExpect(status().isOk()).andExpect(jsonPath("$.token").value(token));
    }

    @Test
    void shouldLoginFacebookWithoutAccessToken_OK() {
        final JwtUserDetails userDetails = buildJwtUserDetails();
        final TokenRequestDTO tokenRequestDTO = TokenRequestDTO.builder().accessToken(randomAlphabetic(3, 10)).build();
        final String jwtToken = randomAlphabetic(3, 10);

        when(userService.loginWithFacebook(tokenRequestDTO.getAccessToken())).thenReturn(userDetails);
        when(jwtTokenService.generateToken(userDetails)).thenReturn(jwtToken);

        final var jwtTokenActual = jwtTokenService.generateToken(userDetails);

        assertEquals(jwtToken, jwtTokenActual);
        assertNotNull(tokenRequestDTO);
    }

    @Test
    void shouldLoginFacebookWithoutAccessToken_ThroughBadRequest() throws Exception {
        final JwtUserDetails userDetails = buildJwtUserDetails();
        final TokenRequestDTO tokenRequestDTO = TokenRequestDTO.builder().accessToken(randomAlphabetic(3, 10)).build();

        when(userService.loginWithFacebook(tokenRequestDTO.getAccessToken())).thenReturn(userDetails);

        post(BASE_URL + "/facebook", null).andExpect(status().isBadRequest());

    void shouldLoginGoogle_OK() throws Exception {
        final var tokenRequest = new TokenRequestDTO(randomAlphabetic(3, 10));
        final var token = randomAlphabetic(3, 10);
        final JwtUserDetails userDetails = buildJwtUserDetails();

        when(googleLoginService.loginGoogle(tokenRequest.getIdToken())).thenReturn(userDetails);
        when(jwtTokenService.generateToken(userDetails)).thenReturn(token);

        post("/api/v1/auth/google", tokenRequest)
                .andExpect(jsonPath("$.token").value(token));

        verify(googleLoginService).loginGoogle(tokenRequest.getIdToken());
        verify(jwtTokenService).generateToken(userDetails);

    }
}