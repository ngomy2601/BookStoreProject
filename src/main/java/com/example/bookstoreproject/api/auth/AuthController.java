package com.example.bookstoreproject.api.auth;

import com.example.bookstoreproject.domain.auths.JwtTokenService;
import com.example.bookstoreproject.domain.auths.JwtUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.bookstoreproject.api.auth.LoginDTOMapper.toAuthentication;

@RestController
@RequestMapping("api/v1/auths")
@RequiredArgsConstructor
public class AuthController {

    private final JwtTokenService jwtTokenService;

    private final AuthenticationManager authenticationManager;

    @PostMapping
    public JwtTokenResponseDTO login(final @RequestBody LoginDTO loginDTO) {
        final Authentication authentication = authenticationManager.authenticate(toAuthentication(loginDTO));

        return JwtTokenResponseDTO.builder()
                .token(jwtTokenService.generateToken((JwtUserDetails) authentication.getPrincipal()))
                .build();
    }
}
