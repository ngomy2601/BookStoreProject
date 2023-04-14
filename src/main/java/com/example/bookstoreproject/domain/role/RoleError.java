package com.example.bookstoreproject.domain.role;

import com.example.bookstoreproject.error.NotFoundException;
import lombok.experimental.UtilityClass;

import java.util.function.Supplier;

@UtilityClass
public class RoleError {

    public static <T> Supplier<NotFoundException> supplyRoleNotFound(final T input) {
        return () -> new NotFoundException("Role %s could not be found", input);
    }
}
