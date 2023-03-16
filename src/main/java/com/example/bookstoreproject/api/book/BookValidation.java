package com.example.bookstoreproject.api.book;

import com.example.bookstoreproject.domain.book.Book;
import lombok.experimental.UtilityClass;

import static com.example.bookstoreproject.error.CommonErrors.supplyValidationError;
import static org.apache.commons.lang3.StringUtils.isBlank;

@UtilityClass
public class BookValidation {
    private static void validateEmptyTitle(final String title) {
        if(isBlank(title)) {
            throw supplyValidationError("Title is empty!").get();
        }
    }

    private static void validateEmptyAuthor(final String author) {
        if(isBlank(author)) {
            throw supplyValidationError("Author is empty!").get();
        }
    }

    public static void validateBookCreation(final Book book) {
        validateEmptyTitle(book.getTitle());
        validateEmptyAuthor(book.getAuthor());
    }

    public static void validateBookUpdate(final Book book) {
        validateEmptyTitle(book.getTitle());
        validateEmptyAuthor(book.getAuthor());
    }
}
