package com.example.bookstoreproject.domain.auths;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SocialTokenPayload {
    private String email;
    private String username;
    private String name;
    private String firstName;
    private String lastName;
    private String avatar;
}
