package com.example.bookstoreproject.fakes;

import com.example.bookstoreproject.domain.auths.JwtUserDetails;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

import static java.util.UUID.randomUUID;

@UtilityClass
public class JwtUserDetailFakes {

    public static JwtUserDetails buildJwtUserDetails() {
        return new JwtUserDetails(randomUUID(), "user@gmail.com", "123123", Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
