package com.example.bookstoreproject.api.book;

import com.example.bookstoreproject.domain.user.User;
import lombok.experimental.UtilityClass;

import java.util.List;

import static org.apache.commons.collections4.ListUtils.emptyIfNull;

@UtilityClass
public class UserDTOMapper {
    public static UserDTO toUserDTO(final User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .avatar(user.getAvatar())
                .role_id(user.getRole_id())
                .build();
    }

    public static List<UserDTO> toUserDTOs(final List<User> users) {
        return emptyIfNull(users)
                .stream()
                .map(UserDTOMapper::toUserDTO)
                .toList();
    }

}