package com.example.bookstoreproject.persistence.user;

import org.junit.jupiter.api.Test;

import static com.example.bookstoreproject.fakes.UserFakes.*;
import static com.example.bookstoreproject.persistence.user.UserEntityMapper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserEntityMapperTest {

    @Test
    void shouldToUser_OK(){
        final var userEntity = buildUserEntity();
        final var user = toUser(userEntity);

        assertEquals(userEntity.getId(), user.getId());
        assertEquals(userEntity.getUsername(), user.getUsername());
        assertEquals(userEntity.getPassword(), user.getPassword());
        assertEquals(userEntity.getFirstName(), user.getFirstName());
        assertEquals(userEntity.getLastName(), user.getLastName());
        assertEquals(userEntity.getAvatar(), user.getAvatar());
        assertEquals(userEntity.getRoleId(), user.getRoleId());
    }

    @Test
    void shouldToUsers_OK(){
        final var userEntity = builderUserEntities();
        final var users = toUsers(userEntity);

        assertEquals(userEntity.size(), users.size());
    }

    @Test
    void shouldToUsersEntity_OK(){
        final var user = buildUser();
        final var userEntity = toUserEntity(user);

        assertEquals(userEntity.getId(), user.getId());
        assertEquals(userEntity.getUsername(), user.getUsername());
        assertEquals(userEntity.getPassword(), user.getPassword());
        assertEquals(userEntity.getFirstName(), user.getFirstName());
        assertEquals(userEntity.getLastName(), user.getLastName());
        assertEquals(userEntity.getAvatar(), user.getAvatar());
        assertEquals(userEntity.getRoleId(), user.getRoleId());
    }
}