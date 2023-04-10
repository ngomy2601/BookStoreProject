package com.example.bookstoreproject.persistence.book;

import com.example.bookstoreproject.domain.book.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.bookstoreproject.persistence.book.BookEntityMapper.*;
import static org.apache.commons.collections4.IterableUtils.toList;

@Repository
@RequiredArgsConstructor
public class BookStore {
    private final BookRepository bookRepository;

    public List<Book> findAll() {
        return toBooks(toList(bookRepository.findAll()));
    }

    public List<Book> find(final String keyword) {
        return toBooks(bookRepository.find(keyword));
    }

    public Book create(final Book book) {
        return toBook(bookRepository.save(toBookEntity(book)));
    }

    public Optional<Book> findById(final UUID id) {
        return bookRepository.findById(id).map(BookEntityMapper::toBook);
    }

    public Optional<Book> findByTitle(final String title) {
        return bookRepository.findByTitle(title).map(BookEntityMapper::toBook);
    }

    public Optional<Book> findBookByIsbn13(final String isbn13) {
        return bookRepository.findByIsbn13(isbn13)
                .map(BookEntityMapper::toBook);
    }

    public Book update(final Book book) {
        return toBook(bookRepository.save(toBookEntity(book)));
    }

    public void delete(final UUID id) {
        bookRepository.deleteById(id);
    }
}
