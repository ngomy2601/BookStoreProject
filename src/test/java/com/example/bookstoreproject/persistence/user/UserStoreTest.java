package com.example.bookstoreproject.persistence.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.bookstoreproject.fakes.UserFakes.buidUserEntity;
import static com.example.bookstoreproject.fakes.UserFakes.builderUserEntities;
import static com.example.bookstoreproject.persistence.user.UserEntityMapper.toUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void shouldCreateUser_OK(){
        final var expected = buidUserEntity();
        final var actual = userStore.createUser(toUser(expected));

        assertEquals(actual.getId(), expected.getId());
        assertEquals(actual.getUsername(), expected.getUsername());
        assertEquals(actual.getPassword(), expected.getPassword());
        assertEquals(actual.getFirstName(), expected.getFirstName());
        assertEquals(actual.getLastName(), expected.getLastName());
        assertEquals(actual.getAvatar(), expected.getAvatar());
        assertEquals(actual.getRoleId(), expected.getRoleId());
    }

}