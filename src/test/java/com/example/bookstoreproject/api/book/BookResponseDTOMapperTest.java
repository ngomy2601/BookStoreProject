package com.example.bookstoreproject.api.book;

import org.junit.jupiter.api.Test;

import static com.example.bookstoreproject.api.book.BookResponseDTOMapper.toBookResponseDTO;
import static com.example.bookstoreproject.api.book.BookResponseDTOMapper.toBookResponseDTOs;
import static com.example.bookstoreproject.fakes.BookFakes.buildBook;
import static com.example.bookstoreproject.fakes.BookFakes.buildBooks;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BookResponseDTOMapperTest {
    @Test
    void shouldToBookResponseDTO_OK() {
        final var book = buildBook();
        final var bookResponseDTODTO = toBookResponseDTO(book);

        assertEquals(book.getId(), bookResponseDTODTO.getId());
        assertEquals(book.getTitle(), bookResponseDTODTO.getTitle());
        assertEquals(book.getAuthor(), bookResponseDTODTO.getAuthor());
        assertEquals(book.getDescription(), bookResponseDTODTO.getDescription());
        assertEquals(book.getCreateAt(), bookResponseDTODTO.getCreateAt());
        assertEquals(book.getUpdateAt(), bookResponseDTODTO.getUpdateAt());
        assertEquals(book.getImage(), bookResponseDTODTO.getImage());
        assertEquals(book.getUserId(), bookResponseDTODTO.getUserId());
    }

    @Test
    void shouldToBookResponseDTOs_OK() {
        final var books = buildBooks();
        final var bookResponseDTOs = toBookResponseDTOs(books);

        assertEquals(books.size(), bookResponseDTOs.size());
    }
}