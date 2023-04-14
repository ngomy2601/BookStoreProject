package com.example.bookstoreproject.fakes;

import com.example.bookstoreproject.domain.book.Book;
import com.example.bookstoreproject.persistence.book.BookEntity;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static java.time.Year.now;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

public class BookFakes {
    public static Book buildBook() {
        return Book.builder()
                .id(UUID.randomUUID())
                .title(randomAlphabetic(5, 15))
                .author(randomAlphabetic(5, 10))
                .description(randomAlphabetic(10, 20))
                .createAt(Instant.now())
                .image(randomAlphabetic(3, 10))
                .subtitle(randomAlphabetic(3, 10))
                .publisher(randomAlphabetic(3, 10))
                .isbn13(randomNumeric(13))
                .price(randomAlphabetic(3, 10))
                .year(generateYear())
                .rating(generateRating())
                .userId(UUID.randomUUID())
                .build();
    }
    public static List<Book> buildBooks() {
        return IntStream.range(1,5)
                .mapToObj(_ignored -> buildBook())
                .toList();
    }
    public static BookEntity buildBookEntity() {
        return BookEntity.builder()
                .id(UUID.randomUUID())
                .title(randomAlphabetic(5, 15))
                .author(randomAlphabetic(5, 10))
                .description(randomAlphabetic(10, 20))
                .createAt(Instant.now())
                .image(randomAlphabetic(3, 10))
                .userId(UUID.randomUUID())
                .build();
    }

    public static List<BookEntity> buildBookEntities() {
        return IntStream.range(1, 5)
                .mapToObj(_ignored -> buildBookEntity())
                .toList();
    }

    public static int generateYear() {
        return now().getValue();
    }

    public static double generateRating() {
        return new SecureRandom().nextDouble() * 5.0;
    }
}
