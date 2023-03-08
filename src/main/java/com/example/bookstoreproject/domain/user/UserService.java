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

    public User create(final User user) {
        return userStore.create(user);
    }

    public User findById(final UUID id) {
        return userStore.findById(id).orElseThrow(supplyUserNotFound(id));
    }

    public User update(final UUID id, final User updatedUser) {
        User user = findById(id);

        user.setUsername(updatedUser.getUsername());
        user.setPassword(updatedUser.getPassword());
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setAvatar(user.getAvatar());
        user.setRoleId(updatedUser.getRoleId());

        return userStore.update(user);
    }

    public void delete(final UUID id) {
        final User user = findById(id);
        userStore.delete(id);
    }
}