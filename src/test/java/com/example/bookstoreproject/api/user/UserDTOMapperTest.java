package com.example.bookstoreproject.api.user;

import org.junit.jupiter.api.Test;

import static com.example.bookstoreproject.api.user.UserRequestDTOMapper.toUserDTO;
import static com.example.bookstoreproject.api.user.UserRequestDTOMapper.toUserDTOs;
import static com.example.bookstoreproject.fakes.UserFakes.buildUser;
import static com.example.bookstoreproject.fakes.UserFakes.buildUsers;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDTOMapperTest {
    @Test
    void shouldToUserDTO_OK(){
        final var user = buildUser();
        final var userDTO = toUserDTO(user);

        assertEquals(user.getId(), userDTO.getId());
        assertEquals(user.getUsername(), userDTO.getUsername());
        assertEquals(user.getPassword(), userDTO.getPassword());
        assertEquals(user.getFirstName(), userDTO.getFirstName());
        assertEquals(user.getLastName(), userDTO.getLastName());
        assertEquals(user.getAvatar(), userDTO.getAvatar());
        assertEquals(user.getRoleId(), userDTO.getRoleId());
    }

    @Test
    void shouldToUserDTOs_OK(){
        final var users = buildUsers();
        final var userDTOs = toUserDTOs(users);
        assertEquals(users.size(), userDTOs.size());
    }

}