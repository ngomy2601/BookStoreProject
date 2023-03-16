package com.example.bookstoreproject.domain.book;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.With;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Builder
@With
public class Book {
    private UUID id;
    private String title;
    private String author;
    private String description;
    private Instant createAt;
    private Instant updateAt;
    private String image;
    private UUID userId;
}
