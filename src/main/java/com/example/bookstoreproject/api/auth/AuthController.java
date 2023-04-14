package com.example.bookstoreproject.api.auth;

import com.example.bookstoreproject.domain.auths.GoogleLoginService;
import com.example.bookstoreproject.domain.auths.JwtTokenService;
import com.example.bookstoreproject.domain.auths.JwtUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.bookstoreproject.api.auth.LoginDTOMapper.toAuthentication;
import static com.example.bookstoreproject.error.CommonErrors.supplyUnauthorizedError;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtTokenService jwtTokenService;

    private final GoogleLoginService googleLoginService;

    private final AuthenticationManager authenticationManager;

    @PostMapping
    public JwtTokenResponseDTO login(final @RequestBody LoginDTO loginDTO) throws Exception {
        try {
            final Authentication authentication = authenticationManager.authenticate(toAuthentication(loginDTO));

            return JwtTokenResponseDTO.builder()
                    .token(jwtTokenService.generateToken((JwtUserDetails) authentication.getPrincipal()))
                    .build();
        } catch (BadCredentialsException | InternalAuthenticationServiceException e) {
            throw supplyUnauthorizedError().get();
        }
    }

    @Operation(summary = "User login by google account")
    @PostMapping("/google")
    public JwtTokenResponseDTO loginGoogle(final @RequestBody TokenRequestDTO tokenRequestDTO) {

        return generateToken((JwtUserDetails) googleLoginService.loginGoogle(tokenRequestDTO.getIdToken()));
    }

    private JwtTokenResponseDTO generateToken(final JwtUserDetails jwtUserDetails) {
        return JwtTokenResponseDTO.builder()
                .token(jwtTokenService.generateToken(jwtUserDetails))
                .build();
    }
}
