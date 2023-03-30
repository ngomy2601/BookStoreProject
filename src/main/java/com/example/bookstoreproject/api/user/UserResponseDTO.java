package com.example.bookstoreproject.api.user;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;
@Getter
@Builder
public class UserResponseDTO {

    private UUID id;
    private String username;
    private String firstName;
    private String lastName;
    private String avatar;
    private UUID roleId;
}
