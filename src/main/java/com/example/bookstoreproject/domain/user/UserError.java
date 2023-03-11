package com.example.bookstoreproject.domain.user;

import com.example.bookstoreproject.error.BadRequestException;
import com.example.bookstoreproject.error.NotFoundException;
import lombok.experimental.UtilityClass;

import java.util.function.Supplier;

@UtilityClass
public class UserError {
    public static <T> Supplier<NotFoundException> supplyUserNotFound(final T input) {
        return () -> new NotFoundException("User with  %s could not be found", input);
    }

    public static Supplier<BadRequestException> supplyUsernameExisted(final String username) {
        return () -> new BadRequestException("User with %s is existed", username);
    }
}
