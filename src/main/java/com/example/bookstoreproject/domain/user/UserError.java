package com.example.bookstoreproject.domain.user;

import com.example.bookstoreproject.error.NotFoundException;
import lombok.experimental.UtilityClass;

import java.util.UUID;
import java.util.function.Supplier;

@UtilityClass
public class UserError {
    public static Supplier<NotFoundException> supplyUserNotFound(final UUID id) {
        return () -> new NotFoundException("User with id %s could not be found", id);
    }
}
