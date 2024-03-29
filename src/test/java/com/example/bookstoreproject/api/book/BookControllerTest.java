package com.example.bookstoreproject.api.book;

import com.example.bookstoreproject.domain.book.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import static java.util.UUID.randomUUID;
import static org.apache.commons.lang3.RandomUtils.nextBytes;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {
    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @Test
    void shouldUploadImage_OK() throws Exception {
        final var id = randomUUID();
        final var bytes = nextBytes(20);
        final var file = new MockMultipartFile("my.png", bytes);
        doNothing().when(bookService).uploadImage(id, bytes);
        bookController.upload(id, file);
        verify(bookService).uploadImage(id, bytes);
    }
}
