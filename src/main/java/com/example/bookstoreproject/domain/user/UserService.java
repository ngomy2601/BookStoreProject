package com.example.bookstoreproject.domain.user;

import com.example.bookstoreproject.persistence.user.UserStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.bookstoreproject.api.user.UserValidation.validateUserCreation;
import static com.example.bookstoreproject.api.user.UserValidation.validateUserUpdate;
import static com.example.bookstoreproject.domain.user.UserError.supplyUserNotFound;
import static com.example.bookstoreproject.domain.user.UserError.supplyUsernameExisted;
import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserStore userStore;


    public List<User> findAll() {
        return userStore.findAll();
    }

    public User create(final User user) {
        validateUserCreation(user);
        checkExistUsername(user.getUsername());
        return userStore.create(user);
    }

    public User findById(final UUID id) {
        return userStore.findById(id).orElseThrow(supplyUserNotFound(id));
    }

    public User findByUsername(final String username) {
        return userStore.findByUsername(username).orElseThrow(supplyUserNotFound(username));
    }

    public void checkExistUsername(final String username) {
        final Optional<User> existUser = userStore.findByUsername(username);
        if (existUser.isPresent()) {
            throw supplyUsernameExisted(username).get();
        }
    }

    public User update(final UUID id, final User updatedUser) {
        final User user = findById(id);
        validateUserUpdate(updatedUser);
        if (!equalsIgnoreCase(user.getUsername(), updatedUser.getUsername())) {
            checkExistUsername(updatedUser.getUsername());
            user.setUsername(updatedUser.getUsername());
        }
        if (isNotBlank(updatedUser.getPassword())) {
            user.setPassword(updatedUser.getPassword());
        }
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setAvatar(updatedUser.getAvatar());
        user.setRoleId(updatedUser.getRoleId());

        return userStore.update(user);
    }

    public void delete(final UUID id) {
        userStore.delete(id);
    }
}