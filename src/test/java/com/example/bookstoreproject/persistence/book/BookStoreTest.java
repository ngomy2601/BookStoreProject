package com.example.bookstoreproject.persistence.book;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.example.bookstoreproject.fakes.BookFakes.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookStoreTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookStore bookStore;

    @Test
    void shouldFindAll_OK() {
        final var expected = buildBookEntities();

        when(bookRepository.findAll()).thenReturn(expected);
        assertEquals(expected.size(), bookStore.findAll().size());

        verify(bookRepository).findAll();
    }

    @Test
    void shouldFindById_OK() {
        final var book = buildBookEntity();
        final var foundBook = Optional.of(book);

        when(bookRepository.findById(book.getId())).thenReturn(foundBook);

        final var actual = bookStore.findById(book.getId()).get();
        final var expected = foundBook.get();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getAuthor(), actual.getAuthor());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getCreateAt(), actual.getCreateAt());
        assertEquals(expected.getImage(), actual.getImage());
        assertEquals(expected.getUserId(), actual.getUserId());

        verify(bookRepository).findById(book.getId());
    }

    @Test
    void shouldFindByTitle_OK() {
        final var book = buildBookEntity();
        final var foundBook = Optional.of(book);

        when(bookRepository.findByTitle(book.getTitle())).thenReturn(foundBook);

        final var actual = bookStore.findByTitle(book.getTitle()).get();
        final var expected = foundBook.get();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getAuthor(), actual.getAuthor());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getCreateAt(), actual.getCreateAt());
        assertEquals(expected.getImage(), actual.getImage());
        assertEquals(expected.getUserId(), actual.getUserId());

        verify(bookRepository).findByTitle(book.getTitle());
    }

    @Test
    void shouldCreateBook_OK() {
        final var expected = buildBookEntity();

        when(bookRepository.save(any())).thenReturn(expected);

        final var actual = bookStore.create(buildBook());

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getAuthor(), actual.getAuthor());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getCreateAt(), actual.getCreateAt());
        assertEquals(expected.getImage(), actual.getImage());
        assertEquals(expected.getUserId(), actual.getUserId());
    }

    @Test
    void shouldUpdateBook_OK() {
        final var expected = buildBookEntity();

        when(bookRepository.save(any())).thenReturn(expected);

        final var actual = bookStore.update(buildBook());

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getAuthor(), actual.getAuthor());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getUpdateAt(), actual.getUpdateAt());
        assertEquals(expected.getImage(), actual.getImage());
        assertEquals(expected.getUserId(), actual.getUserId());
    }

    @Test
    void shouldDeleteBook_OK() {
        final var book = buildBookEntity();
        bookStore.delete(book.getId());
    }
}