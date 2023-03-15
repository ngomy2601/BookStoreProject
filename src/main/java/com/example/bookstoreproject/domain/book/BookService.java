package com.example.bookstoreproject.domain.book;

import com.example.bookstoreproject.persistence.book.BookStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.bookstoreproject.api.book.BookValidation.validateBookCreation;
import static com.example.bookstoreproject.api.book.BookValidation.validateBookUpdate;
import static com.example.bookstoreproject.domain.book.BookError.supplierBookNotFound;
import static com.example.bookstoreproject.domain.book.BookError.supplierBookTitleExisted;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookStore bookStore;

    public List<Book> findAll() {
        return bookStore.findAll();
    }

    public Book create(final Book book) {
        validateBookCreation(book);
        checkExistTitle(book.getTitle());
        book.setCreateAt(Timestamp.from(Instant.now()));
        return bookStore.create(book);
    }

    public Book findById(final UUID id) {
        return bookStore.findById(id).orElseThrow(supplierBookNotFound(id));
    }

    public Book findByTitle(final String title) {
        return bookStore.findByTitle(title).orElseThrow(supplierBookNotFound(title));
    }

    public void checkExistTitle(final String title) {
        final Optional<Book> existBook = bookStore.findByTitle(title);
        if (existBook.isPresent()) {
            throw supplierBookTitleExisted(title).get();
        }
    }

    public Book update(final UUID id, final Book updatedBook) {
        final Book book = findById(id);
        validateBookUpdate(updatedBook);
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setDescription(updatedBook.getDescription());
        book.setUpdateAt(Timestamp.from(Instant.now()));
        book.setImage(updatedBook.getImage());
        book.setUserId(updatedBook.getUserId());
        return bookStore.update(book);
    }

    public void delete(final UUID id) {
        bookStore.delete(id);
    }
}
