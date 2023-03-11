package com.example.bookstoreproject.api.user;

import com.example.bookstoreproject.domain.user.User;
import lombok.experimental.UtilityClass;

import static com.example.bookstoreproject.error.CommonErrors.supplyValidationError;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@UtilityClass
public class UserValidation {
    private static void validateEmptyUsername(final String username) {
        if (isBlank(username)) {
            throw supplyValidationError("Username is empty!").get();
        }
    }

    private static void validateLengthOfPassword(final String password) {
        if (isNotBlank(password) && password.length() < 6) {
            throw supplyValidationError("Password must be at least 6 characters!").get();
        }
    }

    private static void validateEmptyPassword(final String password) {
        if (isBlank(password)) {
            throw supplyValidationError("Password is empty!").get();
        }
    }

    public static void validateUserCreation(final User user) {
        validateEmptyUsername(user.getUsername());
        validateEmptyPassword(user.getPassword());
        validateLengthOfPassword(user.getPassword());
    }

    public static void validateUserUpdate(final User user) {
        validateEmptyUsername(user.getUsername());
        validateLengthOfPassword(user.getPassword());
    }
}
