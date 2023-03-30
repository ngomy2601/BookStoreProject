package com.example.bookstoreproject.api.user;

import com.example.bookstoreproject.domain.user.User;

import java.util.List;

import static org.apache.commons.collections4.ListUtils.emptyIfNull;

public class UserResponseDTOMapper {
    public static UserResponseDTO toUserResponseDTO(final User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .avatar(user.getAvatar())
                .roleId(user.getRoleId())
                .build();
    }

    public static List<UserResponseDTO> toUserResponseDTOs(final List<User> users) {
        return emptyIfNull(users)
                .stream()
                .map(UserResponseDTOMapper::toUserResponseDTO)
                .toList();
    }
}
