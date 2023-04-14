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
        final var bookResponseDTO = toBookResponseDTO(book);

        assertEquals(book.getId(), bookResponseDTO.getId());
        assertEquals(book.getTitle(), bookResponseDTO.getTitle());
        assertEquals(book.getAuthor(), bookResponseDTO.getAuthor());
        assertEquals(book.getDescription(), bookResponseDTO.getDescription());
        assertEquals(book.getCreateAt(), bookResponseDTO.getCreateAt());
        assertEquals(book.getUpdateAt(), bookResponseDTO.getUpdateAt());
        assertEquals(book.getImage(), bookResponseDTO.getImage());
        assertEquals(book.getSubtitle(), bookResponseDTO.getSubtitle());
        assertEquals(book.getPublisher(), bookResponseDTO.getPublisher());
        assertEquals(book.getIsbn13(), bookResponseDTO.getIsbn13());
        assertEquals(book.getPrice(), bookResponseDTO.getPrice());
        assertEquals(book.getYear(), bookResponseDTO.getYear());
        assertEquals(book.getRating(), bookResponseDTO.getRating());
        assertEquals(book.getUserId(), bookResponseDTO.getUserId());
    }

    @Test
    void shouldToBookResponseDTOs_OK() {
        final var books = buildBooks();
        final var bookResponseDTOs = toBookResponseDTOs(books);

        assertEquals(books.size(), bookResponseDTOs.size());
    }
}