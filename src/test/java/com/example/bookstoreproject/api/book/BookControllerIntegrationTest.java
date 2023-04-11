package com.example.bookstoreproject.api.book;

import com.example.bookstoreproject.api.AbstractControllerTest;
import com.example.bookstoreproject.api.WithMockAdmin;
import com.example.bookstoreproject.api.WithMockUser;
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
class BookControllerIntegrationTest extends AbstractControllerTest {

    private static final String BASE_URL = "/api/v1/books";

    @MockBean
    private BookService bookService;

    @Test
    @WithMockAdmin
    @WithMockUser
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
                .andExpect(jsonPath("$[0].subtitle").value(books.get(0).getSubtitle()))
                .andExpect(jsonPath("$[0].publisher").value(books.get(0).getPublisher()))
                .andExpect(jsonPath("$[0].isbn13").value(books.get(0).getIsbn13()))
                .andExpect(jsonPath("$[0].price").value(books.get(0).getPrice()))
                .andExpect(jsonPath("$[0].year").value(books.get(0).getYear()))
                .andExpect(jsonPath("$[0].rating").value(books.get(0).getRating()))
                .andExpect(jsonPath("$[0].userId").value(books.get(0).getUserId().toString()));

        verify(bookService).findAll();
    }

    @Test
    @WithMockAdmin
    @WithMockUser
    void shouldFindById_OK() throws Exception {
        final var book = buildBook();

        when(bookService.findById(book.getId())).thenReturn(book);

        get(BASE_URL + "/" + book.getId())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(book.getId().toString()))
                .andExpect(jsonPath("$.title").value(book.getTitle()))
                .andExpect(jsonPath("$.author").value(book.getAuthor()))
                .andExpect(jsonPath("$.description").value(book.getDescription()))
                .andExpect(jsonPath("$.createAt").value(book.getCreateAt().toString()))
                .andExpect(jsonPath("$.image").value(book.getImage()))
                .andExpect(jsonPath("$.subtitle").value(book.getSubtitle()))
                .andExpect(jsonPath("$.publisher").value(book.getPublisher()))
                .andExpect(jsonPath("$.isbn13").value(book.getIsbn13()))
                .andExpect(jsonPath("$.price").value(book.getPrice()))
                .andExpect(jsonPath("$.year").value(book.getYear()))
                .andExpect(jsonPath("$.rating").value(book.getRating()))
                .andExpect(jsonPath("$.userId").value(book.getUserId().toString()));

        verify(bookService).findById(book.getId());
    }

    @Test
    @WithMockAdmin
    @WithMockUser
    void shouldCreateBook_OK() throws Exception {
        final var book = buildBook();

        when(bookService.create(any())).thenReturn(book);

        post(BASE_URL, book)
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
    @WithMockAdmin
    @WithMockUser
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
    @WithMockAdmin
    void shouldFind_OK() throws Exception {
        final var book = buildBook();
        final var expected = buildBooks();

        when(bookService.find(book.getTitle())).thenReturn(expected);

        get(BASE_URL + "/find?keyword=" + book.getTitle())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(expected.size()))
                .andExpect(jsonPath("$[0].id").value(expected.get(0).getId().toString()))
                .andExpect(jsonPath("$[0].title").value(expected.get(0).getTitle()))
                .andExpect(jsonPath("$[0].author").value(expected.get(0).getAuthor()))
                .andExpect(jsonPath("$[0].description").value(expected.get(0).getDescription()))
                .andExpect(jsonPath("$[0].userId").value(expected.get(0).getUserId().toString()));

        verify(bookService).find(book.getTitle());
    }

    @Test
    void shouldThrowExceptionWhenUpdate_OK() throws Exception {
        final var book = buildBook();

        when(bookService.update(any(), any())).thenReturn(book);

        put(BASE_URL + "/" + book.getId(), book)
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockAdmin
    @WithMockUser
    void shouldDeleteBook_OK() throws Exception {
        final var book = buildBook();
        delete(BASE_URL + "/" + book.getId())
                .andExpect(status().isOk());
    }

    @Test
    void shouldThrowExceptionWhenDelete_OK() throws Exception {
        final var book = buildBook();
        delete(BASE_URL + "/" + book.getId())
                .andExpect(status().isUnauthorized());
    }
}