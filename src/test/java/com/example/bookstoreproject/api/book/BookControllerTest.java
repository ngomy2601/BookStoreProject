package com.example.bookstoreproject.api.book;

import com.example.bookstoreproject.api.AbstractControllerTest;
import com.example.bookstoreproject.domain.book.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.example.bookstoreproject.fakes.BookFakes.buildBook;
import static com.example.bookstoreproject.fakes.BookFakes.buildBooks;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BookController.class)
@AutoConfigureMockMvc
class BookControllerTest extends AbstractControllerTest {

    private static final String BASE_URL = "/api/v1/books";

    @MockBean
    private BookService bookService;

    @Test
    void shouldFindAll_OK() throws Exception {
        final var books = buildBooks();

        when(bookService.findAll()).thenReturn(books);

        get(BASE_URL)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(books.size()))
                .andExpect(jsonPath("$[0].id").value(books.get(0).getId().toString()))
                .andExpect(jsonPath("$[0].title").value(books.get(0).getTitle()))
                .andExpect(jsonPath("$[0].author").value(books.get(0).getAuthor()))
                .andExpect(jsonPath("$[0].description").value(books.get(0).getDescription()))
                .andExpect(jsonPath("$[0].createAt").value(books.get(0).getCreateAt().toString()))
                .andExpect(jsonPath("$[0].image").value(books.get(0).getImage()))
                .andExpect(jsonPath("$[0].userId").value(books.get(0).getUserId().toString()));

        verify(bookService).findAll();
    }

    @Test
    void shouldCreateBook_OK() throws Exception {
        final var book = buildBook();

        when(bookService.create(any())).thenReturn(book);

        post(BASE_URL, book)
                .andExpect(jsonPath("$.id").value(book.getId().toString()))
                .andExpect(jsonPath("$.title").value(book.getTitle()))
                .andExpect(jsonPath("$.author").value(book.getAuthor()))
                .andExpect(jsonPath("$.description").value(book.getDescription()))
                .andExpect(jsonPath("$.createAt").value(book.getCreateAt().toString()))
                .andExpect(jsonPath("$.image").value(book.getImage()))
                .andExpect(jsonPath("$.userId").value(book.getUserId().toString()));
    }

    @Test
    void shouldUpdateBook_OK() throws Exception {
        final var book = buildBook();

        when(bookService.update(any(), any())).thenReturn(book);

        put(BASE_URL + "/" + book.getId(), book)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(book.getId().toString()))
                .andExpect(jsonPath("$.title").value(book.getTitle()))
                .andExpect(jsonPath("$.author").value(book.getAuthor()))
                .andExpect(jsonPath("$.description").value(book.getDescription()))
                .andExpect(jsonPath("$.createAt").value(book.getCreateAt().toString()))
                .andExpect(jsonPath("$.image").value(book.getImage()))
                .andExpect(jsonPath("$.userId").value(book.getUserId().toString()));
    }

    @Test
    void shouldDeleteBook_OK() throws Exception {
        final var book = buildBook();
        delete(BASE_URL + "/" + book.getId())
                .andExpect(status().isOk());
    }
}