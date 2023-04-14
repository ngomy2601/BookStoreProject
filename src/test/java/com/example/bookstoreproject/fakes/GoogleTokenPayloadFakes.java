package com.example.bookstoreproject.fakes;

import com.example.bookstoreproject.domain.auths.SocialTokenPayload;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GoogleTokenPayloadFakes {

    public static SocialTokenPayload buildToken() {
        return SocialTokenPayload.builder()
                .email("user@gmail.com")
                .username("myngo")
                .name("my")
                .firstName("test")
                .lastName("test")
                .avatar("avatar.png")
                .build();
    }
}
