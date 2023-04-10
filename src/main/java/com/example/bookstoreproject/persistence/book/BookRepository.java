package com.example.bookstoreproject.persistence.book;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, UUID> {
    Optional<BookEntity> findByTitle(final String title);

    List<BookEntity> find(final String keyword);

    Optional<BookEntity> findByIsbn13(final String isbn13);
}
