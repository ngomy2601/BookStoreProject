package com.example.bookstoreproject.domain.auths;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GoogleTokenPayload {
    private String email;
    private String firstName;
    private String lastName;
    private String avatar;
}
