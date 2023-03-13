package com.example.bookstoreproject.domain.book;

import com.example.bookstoreproject.persistence.book.BookStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.bookstoreproject.domain.book.BookError.supplierBookNotFound;
import static com.example.bookstoreproject.domain.book.BookError.supplierBookTitleExisted;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookStore bookStore;

    public List<Book> findAll() {
        return bookStore.findAll();
    }

    public Book create(final Book book){
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
        if(existBook.isPresent()) {
            throw supplierBookTitleExisted(title).get();
        }
    }

    public void delete(final UUID id) {
        bookStore.delete(id);
    }
}
