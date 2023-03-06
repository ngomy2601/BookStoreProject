package com.example.bookstoreproject.domain.user;

import com.example.bookstoreproject.api.book.UserDTO;

public class UserDomainMapper {
    public static User toUser(final UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .avatar(userDTO.getAvatar())
                .roleId(userDTO.getRole_id())
                .build();
    }

}
