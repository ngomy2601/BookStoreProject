package com.example.bookstoreproject.api.user;

import com.example.bookstoreproject.domain.user.User;
import lombok.experimental.UtilityClass;

import java.util.List;

import static org.apache.commons.collections4.ListUtils.emptyIfNull;

@UtilityClass
public class UserRequestDTOMapper {
    public static UserRequestDTO toUserDTO(final User user) {
        return UserRequestDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .avatar(user.getAvatar())
                .roleId(user.getRoleId())
                .build();
    }

    public static List<UserRequestDTO> toUserDTOs(final List<User> users) {
        return emptyIfNull(users)
                .stream()
                .map(UserRequestDTOMapper::toUserDTO)
                .toList();
    }

}
