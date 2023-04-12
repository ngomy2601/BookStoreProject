package com.example.bookstoreproject.fakes;

import com.example.bookstoreproject.domain.auths.GoogleTokenPayload;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GoogleTokenPayloadFakes {

    public static GoogleTokenPayload buildToken() {
        return GoogleTokenPayload.builder()
                .email("user@gmail.com")
                .firstName("test")
                .lastName("test")
                .avatar("avatar.png")
                .build();
    }
}
