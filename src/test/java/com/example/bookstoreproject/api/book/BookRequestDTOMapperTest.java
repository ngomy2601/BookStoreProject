package com.example.bookstoreproject.api.book;

import org.junit.jupiter.api.Test;

import static com.example.bookstoreproject.api.book.BookRequestDTOMapper.toBookRequestDTO;
import static com.example.bookstoreproject.api.book.BookRequestDTOMapper.toBookRequestDTOs;
import static com.example.bookstoreproject.fakes.BookFakes.buildBook;
import static com.example.bookstoreproject.fakes.BookFakes.buildBooks;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BookRequestDTOMapperTest {

    @Test
    void shouldToBookDTO_OK() {
        final var book = buildBook();
        final var bookDTO = toBookRequestDTO(book);

        assertEquals(book.getId(), bookDTO.getId());
        assertEquals(book.getTitle(), bookDTO.getTitle());
        assertEquals(book.getAuthor(), bookDTO.getAuthor());
        assertEquals(book.getDescription(), bookDTO.getDescription());
        assertEquals(book.getImage(), bookDTO.getImage());
    }

    @Test
    void shouldToBookDTOs_OK() {
        final var books = buildBooks();
        final var bookDTOs = toBookRequestDTOs(books);

        assertEquals(books.size(), bookDTOs.size());
    }

}