package com.example.bookstoreproject.fakes;

import com.example.bookstoreproject.integration.ItBookDetailDTO;

import static com.example.bookstoreproject.fakes.BookFakes.generateRating;
import static com.example.bookstoreproject.fakes.BookFakes.generateYear;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

public class FetchBookFakes {
    public static ItBookDetailDTO buildBookItemDetailDTO() {
        return ItBookDetailDTO.builder()
                .title(randomAlphabetic(3, 10))
                .authors(randomAlphabetic(3, 10))
                .desc(randomAlphabetic(3, 10))
                .image(randomAlphabetic(3, 10))
                .subtitle(randomAlphabetic(3, 10))
                .publisher(randomAlphabetic(3, 10))
                .isbn13(randomNumeric(13))
                .price(randomAlphabetic(3, 10))
                .year(generateYear())
                .rating(generateRating())
                .build();
    }
}
