package com.example.bookstoreproject.fakes;

import com.example.bookstoreproject.domain.auths.SocialTokenPayload;

public class SocialTokenPayloadFakes {

    public static SocialTokenPayload buildTokenSocial() {
        return SocialTokenPayload.builder()
                .name("my2601")
                .username("tieumy")
                .firstName("my")
                .lastName("ngo")
                .build();
    }
}
