package com.example.bookstoreproject.persistence.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.example.bookstoreproject.fakes.UserFakes.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserStoreTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserStore userStore;

    @Test
    void shouldFindAll_OK() {
        final var expected = builderUserEntities();

        when(userRepository.findAll())
                .thenReturn(expected);

        assertEquals(expected.size(), userStore.findAll().size());

        verify(userRepository).findAll();
    }

    @Test
    void shouldFindById_OK() {
        final var user = buildUserEntity();
        final var foundUser = Optional.of(user);
        when(userRepository.findById(user.getId())).thenReturn(foundUser);
        final var actual = userStore.findById(user.getId()).get();
        final var expected = foundUser.get();
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getAvatar(), actual.getAvatar());
        assertEquals(expected.getRoleId(), actual.getRoleId());
        verify(userRepository).findById(user.getId());
    }

    @Test
    void shouldFindByUsername_OK() {
        final var user = buildUserEntity();
        final var foundUser = Optional.of(user);
        when(userRepository.findByUsername(user.getUsername())).thenReturn(foundUser);
        final var actual = userStore.findByUsername(user.getUsername()).get();
        final var expected = foundUser.get();
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getAvatar(), actual.getAvatar());
        assertEquals(expected.getRoleId(), actual.getRoleId());
        verify(userRepository).findByUsername(user.getUsername());
    }

    @Test
    void shouldCreateUser_OK() {
        final var expected = buildUserEntity();
        when(userRepository.save(any())).thenReturn(expected);

        final var actual = userStore.create(buildUser());

        assertEquals(actual.getId(), expected.getId());
        assertEquals(actual.getUsername(), expected.getUsername());
        assertEquals(actual.getPassword(), expected.getPassword());
        assertEquals(actual.getFirstName(), expected.getFirstName());
        assertEquals(actual.getLastName(), expected.getLastName());
        assertEquals(actual.getAvatar(), expected.getAvatar());
        assertEquals(actual.getRoleId(), expected.getRoleId());
    }

    @Test
    void shouldUpdateUser_OK() {
        final var expected = buildUserEntity();
        when(userRepository.save(any())).thenReturn(expected);

        final var actual = userStore.update(buildUser());
        assertEquals(actual.getId(), expected.getId());
        assertEquals(actual.getUsername(), expected.getUsername());
        assertEquals(actual.getPassword(), expected.getPassword());
        assertEquals(actual.getFirstName(), expected.getFirstName());
        assertEquals(actual.getLastName(), expected.getLastName());
        assertEquals(actual.getAvatar(), expected.getAvatar());
        assertEquals(actual.getRoleId(), expected.getRoleId());
    }

    @Test
    void shouldDeleteUser_OK() {
        final var user = buildUserEntity();
        userStore.delete(user.getId());

    }
}