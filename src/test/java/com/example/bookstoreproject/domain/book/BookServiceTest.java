package com.example.bookstoreproject.domain.book;

import com.example.bookstoreproject.domain.auths.AuthsProvider;
import com.example.bookstoreproject.persistence.book.BookStore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.example.bookstoreproject.fakes.BookFakes.buildBook;
import static com.example.bookstoreproject.fakes.BookFakes.buildBooks;
import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookStore bookStore;

    @Mock
    private AuthsProvider authsProvider;

    @InjectMocks
    private BookService bookService;


    @Test
    void shouldFindAll_OK() {
        final var expected = buildBooks();

        when(bookStore.findAll()).thenReturn(expected);

        final var actual = bookService.findAll();

        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0).getId(), actual.get(0).getId());
        assertEquals(expected.get(0).getTitle(), actual.get(0).getTitle());
        assertEquals(expected.get(0).getAuthor(), actual.get(0).getAuthor());
        assertEquals(expected.get(0).getDescription(), actual.get(0).getDescription());
        assertEquals(expected.get(0).getCreateAt(), actual.get(0).getCreateAt());
        assertEquals(expected.get(0).getImage(), actual.get(0).getImage());
        assertEquals(expected.get(0).getUserId(), actual.get(0).getUserId());

        verify(bookStore).findAll();
    }

    @Test
    void shouldFindById_OK() {
        final var book = buildBook();

        when(bookStore.findById(book.getId())).thenReturn(Optional.of(book));

        final var foundBook = bookService.findById(book.getId());

        assertEquals(book, foundBook);

        verify(bookStore).findById(book.getId());
    }

    @Test
    void shouldCreateBook_OK() {
        final var userId = randomUUID();
        final var book = buildBook()
                .withRating(0.0)
                .withUserId(userId);

        when(authsProvider.getCurrentUserId()).thenReturn(userId);
        when(bookStore.create(any(Book.class))).thenReturn(book);

        final var createdBook = bookService.create(book);

        assertEquals(book.getId(), createdBook.getId());
        assertEquals(book.getTitle(), createdBook.getTitle());
        assertEquals(book.getAuthor(), createdBook.getAuthor());
        assertEquals(book.getDescription(), createdBook.getDescription());
        assertEquals(book.getCreateAt(), createdBook.getCreateAt());
        assertEquals(book.getImage(), createdBook.getImage());
        assertEquals(book.getUserId(), userId);

        verify(authsProvider).getCurrentUserId();
        verify(bookStore).create(any(Book.class));
    }

    @Test
    void shouldUpdateBook_OK() {
        final var book = buildBook().withIsbn13("978-1-56619-909-4");
        final var updatedBook = buildBook().withId(book.getId());

        when(bookStore.findById(book.getId())).thenReturn(Optional.of(book));
        when(bookStore.update(book)).thenReturn(book);

        final var expected = bookService.update(book.getId(), updatedBook);

        assertEquals(expected.getId(), updatedBook.getId());
        assertEquals(expected.getTitle(), updatedBook.getTitle());
        assertEquals(expected.getAuthor(), updatedBook.getAuthor());
        assertEquals(expected.getDescription(), updatedBook.getDescription());
        assertEquals(expected.getImage(), updatedBook.getImage());
        verify(bookStore).update(book);
    }

    @Test
    void shouldDeleteBook_OK() {
        final var book = buildBook();
        bookService.delete(book.getId());
        verify(bookStore).delete(book.getId());
    }
    
}