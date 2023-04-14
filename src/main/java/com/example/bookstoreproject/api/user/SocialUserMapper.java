package com.example.bookstoreproject.api.user;

import com.example.bookstoreproject.domain.user.SocialUser;
import com.example.bookstoreproject.domain.user.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SocialUserMapper {

    public static SocialUser toSocialUser(final User user) {
        return SocialUser.builder()
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
