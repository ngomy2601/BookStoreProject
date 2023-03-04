package com.example.bookstoreproject.domain.user;

import com.example.bookstoreproject.persistence.user.UserStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.example.bookstoreproject.domain.user.UserError.supplyUserNotFound;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserStore userStore;

    public List<User> findAll() {
        return userStore.findAll();
    }

    public User createUser(final User user) {
        return userStore.createUser(user);
    }

    public User findUserById(final UUID id) {
        return userStore.findUserById(id).orElseThrow(supplyUserNotFound(id));
    }

    public User updateUser(final UUID id, final User updatedUser) {
        User user = findUserById(id);

        user.setUsername(updatedUser.getUsername());
        user.setPassword(updatedUser.getPassword());
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setAvatar(user.getAvatar());
        user.setRole_id(updatedUser.getRole_id());

        return userStore.updateUser(user);
    }

    public void deleteUser(final UUID id) {
        userStore.deleteUser(id);
    }
}