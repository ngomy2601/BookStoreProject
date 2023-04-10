package com.example.bookstoreproject.domain.book;

import com.example.bookstoreproject.domain.auths.AuthsProvider;
import com.example.bookstoreproject.persistence.book.BookStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.bookstoreproject.api.book.BookValidation.validateBookCreation;
import static com.example.bookstoreproject.api.book.BookValidation.validateBookUpdate;
import static com.example.bookstoreproject.domain.book.BookError.*;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookStore bookStore;
    private final CloudinaryService cloudinaryService;
    private final AuthsProvider authsProvider;

    public List<Book> findAll() {
        return bookStore.findAll();
    }

    public List<Book> find(final String keyword) {
        return bookStore.find(keyword);
    }

    public Book create(final Book book) {
        validateBookCreation(book);
        checkExistTitle(book.getTitle());
        verifyIsbn13BookAvailable(book.getIsbn13());

        final double bookRating = book.getRating() == null ? 0.0 : book.getRating();
        final Book createdBook = book
                .withUserId(authsProvider.getCurrentUserId())
                .withIsbn13(book.getIsbn13())
                .withCreateAt(Instant.now())
                .withRating(bookRating);
        return bookStore.create(createdBook);
    }

    public Book findById(final UUID id) {
        return bookStore.findById(id).orElseThrow(supplierBookNotFound(id));
    }

    public Book findByTitle(final String title) {
        return bookStore.findByTitle(title).orElseThrow(supplierBookNotFound(title));
    }

    public Book findBookByIsbn13(final String isbn13) {
        return bookStore.findBookByIsbn13(isbn13)
                .orElseThrow(supplierBookNotFound(isbn13));
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
        if (!book.getIsbn13().equals(book.getIsbn13())) {
            verifyIsbn13BookAvailable(book.getIsbn13());
            book.setIsbn13(book.getIsbn13());
        }
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setDescription(updatedBook.getDescription());
        book.setUpdateAt(Instant.now());
        book.setImage(updatedBook.getImage());
        book.setSubtitle(updatedBook.getSubtitle());
        book.setPublisher(updatedBook.getPublisher());
        book.setPrice(updatedBook.getPrice());
        book.setYear(updatedBook.getYear());
        book.setRating(updatedBook.getRating());
        return bookStore.update(book);
    }

    public void delete(final UUID id) {
        bookStore.delete(id);
    }

    public void uploadImage(final UUID id, final byte[] image) throws IOException {
        final Book book = findById(id);
        book.setImage(cloudinaryService.upload(image));
        book.setUpdateAt(Instant.now());
        bookStore.update(book);
    }

    private void verifyIsbn13BookAvailable(final String isbn13) {
        bookStore.findBookByIsbn13(isbn13)
                .ifPresent(b -> {
                    throw supplyIsbn13BookAlreadyExist(isbn13).get();
                });
    }
}
