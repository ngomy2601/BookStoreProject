package com.example.bookstoreproject.api.book;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
public class BookResponseDTO {
    private UUID id;
    private String title;
    private String author;
    private String description;
    private Instant createAt;
    private Instant updateAt;
    private String image;
    private String subtitle;
    private String publisher;
    private String isbn13;
    private String price;
    private Integer year;
    private Double rating;
    private UUID userId;
}
