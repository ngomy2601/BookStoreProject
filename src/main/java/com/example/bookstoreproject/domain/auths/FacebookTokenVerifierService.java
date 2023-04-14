package com.example.bookstoreproject.domain.auths;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Service;

import static com.example.bookstoreproject.domain.auths.SocialTokenPayloadMapper.toSocialTokenPayload;

@Service

public class FacebookTokenVerifierService {

    public SocialTokenPayload verifyFacebookToken(final String idToken) {
        final Facebook facebook = new FacebookTemplate(idToken);
        final User user = facebook.fetchObject("me", User.class);
        return toSocialTokenPayload(user);
    }
}
