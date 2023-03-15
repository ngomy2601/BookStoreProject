package com.example.bookstoreproject.persistence.book;

import org.junit.jupiter.api.Test;

import static com.example.bookstoreproject.fakes.BookFakes.*;
import static com.example.bookstoreproject.persistence.book.BookEntityMapper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BookEntityMapperTest {
    @Test
    void shouldToBook_OK() {
        final var bookEntity = buildBookEntity();
        final var book = toBook(bookEntity);
        assertEquals(bookEntity.getId(), book.getId());
        assertEquals(bookEntity.getTitle(), book.getTitle());
        assertEquals(bookEntity.getAuthor(), book.getAuthor());
        assertEquals(bookEntity.getDescription(), book.getDescription());
        assertEquals(bookEntity.getCreateAt(), book.getCreateAt());
        assertEquals(bookEntity.getImage(), book.getImage());
        assertEquals(bookEntity.getUserId(), book.getUserId());
    }

    @Test
    void shouldToBooks_OK() {
        final var bookEntity = buildBookEntities();
        final var books = toBooks(bookEntity);
        assertEquals(bookEntity.size(), books.size());
    }

    @Test
    void shouldToBooksEntity_OK() {
        final var book = buildBook();
        final var bookEntity = toBookEntity(book);
        assertEquals(bookEntity.getId(), book.getId());
        assertEquals(bookEntity.getTitle(), book.getTitle());
        assertEquals(bookEntity.getAuthor(), book.getAuthor());
        assertEquals(bookEntity.getDescription(), book.getDescription());
        assertEquals(bookEntity.getCreateAt(), book.getCreateAt());
        assertEquals(bookEntity.getImage(), book.getImage());
        assertEquals(bookEntity.getUserId(), book.getUserId());
    }
}