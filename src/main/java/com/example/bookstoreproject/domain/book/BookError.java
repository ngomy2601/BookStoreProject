package com.example.bookstoreproject.domain.book;

import com.example.bookstoreproject.error.BadRequestException;
import com.example.bookstoreproject.error.NotFoundException;
import lombok.experimental.UtilityClass;

import java.util.function.Supplier;

@UtilityClass
public class BookError {
    public static <T> Supplier<NotFoundException> supplierBookNotFound(final T input) {
        return () -> new NotFoundException("Book with %s could not be found", input);
    }

    public static Supplier<BadRequestException> supplierBookTitleExisted(final String title) {
        return () -> new BadRequestException("Book with %s is existed", title);
    }
}
