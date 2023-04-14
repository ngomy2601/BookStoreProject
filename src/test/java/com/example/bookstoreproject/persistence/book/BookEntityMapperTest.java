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
        assertEquals(bookEntity.getSubtitle(), book.getSubtitle());
        assertEquals(bookEntity.getPublisher(), book.getPublisher());
        assertEquals(bookEntity.getIsbn13(), book.getIsbn13());
        assertEquals(bookEntity.getPrice(), book.getPrice());
        assertEquals(bookEntity.getYear(), book.getYear());
        assertEquals(bookEntity.getRating(), book.getRating());
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
        assertEquals(book.getSubtitle(), bookEntity.getSubtitle());
        assertEquals(book.getPublisher(), bookEntity.getPublisher());
        assertEquals(book.getIsbn13(), bookEntity.getIsbn13());
        assertEquals(book.getPrice(), bookEntity.getPrice());
        assertEquals(book.getYear(), bookEntity.getYear());
        assertEquals(book.getRating(), bookEntity.getRating());
        assertEquals(bookEntity.getUserId(), book.getUserId());
    }
}