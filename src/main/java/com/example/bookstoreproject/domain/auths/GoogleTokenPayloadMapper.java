package com.example.bookstoreproject.domain.auths;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GoogleTokenPayloadMapper {
    public static SocialTokenPayload toGoogleTokenPayload(final GoogleIdToken.Payload payload) {
        return SocialTokenPayload.builder()
                .email(payload.getEmail())
                .firstName((String) payload.get("family_name"))
                .lastName((String) payload.get("given_name"))
                .avatar((String) payload.get("picture"))
                .build();
    }
}
