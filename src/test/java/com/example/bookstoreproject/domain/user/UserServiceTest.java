package com.example.bookstoreproject.domain.user;

import com.example.bookstoreproject.persistence.user.UserStore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.example.bookstoreproject.fakes.UserFakes.buildUser;
import static com.example.bookstoreproject.fakes.UserFakes.buildUsers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class UserServiceTest {
    @Mock
    private UserStore userStore;


    @InjectMocks
    private UserService userService;

    @Test
    void shouldFindAll_OK(){
        final var expected = buildUsers();
        when(userStore.findAll())
                .thenReturn(expected);
        final var actual = userService.findAll();
        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0).getId(), actual.get(0).getId());
        assertEquals(expected.get(0).getUsername(), actual.get(0).getUsername());
        assertEquals(expected.get(0).getPassword(), actual.get(0).getPassword());
        assertEquals(expected.get(0).getFirstName(), actual.get(0).getFirstName());
        assertEquals(expected.get(0).getLastName(), actual.get(0).getLastName());
        assertEquals(expected.get(0).getAvatar(), actual.get(0).getAvatar());
        assertEquals(expected.get(0).getRoleId(), actual.get(0).getRoleId());
        verify(userStore).findAll();
    }

    @Test
    void shouldFindById_OK() {
        final var user = buildUser();
        when(userStore.findById(user.getId())).thenReturn(Optional.of(user));
        final var foundUser = userService.findById(user.getId());
        assertEquals(user, foundUser);
        verify(userStore).findById(user.getId());
    }

    @Test
    void shouldCreateUser_OK() {
        final var user = buildUser();
        when(userStore.create(user)).thenReturn(user);
        final var createdUser = userService.create(user);
        assertEquals(user.getId(), createdUser.getId());
        assertEquals(user.getUsername(), createdUser.getUsername());
        assertEquals(user.getFirstName(), createdUser.getFirstName());
        assertEquals(user.getLastName(), createdUser.getLastName());
        assertEquals(user.getAvatar(), createdUser.getAvatar());
        assertEquals(user.getRoleId(), createdUser.getRoleId());
        verify(userStore).create(user);
    }

    @Test
    void shouldUpdateUser_OK() {
        final var user = buildUser();
        final var updatedUser = buildUser()
                .withId(user.getId())
                .withRoleId(user.getRoleId());
        when(userStore.findById(user.getId())).thenReturn(Optional.of(user));
        when(userStore.update(user)).thenReturn(user);
        final var expected = userService.update(user.getId(), updatedUser);
        assertEquals(expected.getId(), updatedUser.getId());
        assertEquals(expected.getUsername(), updatedUser.getUsername());
        assertEquals(expected.getFirstName(), updatedUser.getFirstName());
        assertEquals(expected.getLastName(), updatedUser.getLastName());
        assertEquals(expected.getAvatar(), updatedUser.getAvatar());
        assertEquals(expected.getRoleId(), updatedUser.getRoleId());
    }

    @Test
    void shouldDeleteUser_OK() {
        final var user = buildUser();
        userService.delete(user.getId());
        verify(userStore).delete(user.getId());
    }
}