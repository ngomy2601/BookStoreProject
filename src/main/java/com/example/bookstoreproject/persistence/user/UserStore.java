package com.example.bookstoreproject.persistence.user;

import com.example.bookstoreproject.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.bookstoreproject.persistence.user.UserEntityMapper.*;
import static org.apache.commons.collections4.IterableUtils.toList;

@Repository
@RequiredArgsConstructor
public class UserStore {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return toUsers(toList(userRepository.findAll()));
    }

    public User create(final User user) {
        return toUser(userRepository.save(toUserEntity(user)));
    }

    public Optional<User> findById(final UUID id) {
        return userRepository.findById(id).map(UserEntityMapper::toUser);
    }

    public Optional<User> findByUsername(final String username) {
        return userRepository.findByUsername(username).map(UserEntityMapper::toUser);
    }

    public User update(final User user) {
        return toUser(userRepository.save(toUserEntity(user)));
    }

    public void delete(final UUID id) {
        userRepository.deleteById(id);
    }
}
