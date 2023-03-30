package com.example.bookstoreproject.domain.book;

import com.example.bookstoreproject.api.book.BookRequestDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BookDomainMapper {
    public static Book toBook(final BookRequestDTO bookDTO) {
        return Book.builder()
                .id(bookDTO.getId())
                .title(bookDTO.getTitle())
                .author(bookDTO.getAuthor())
                .description(bookDTO.getDescription())
                .image(bookDTO.getImage())
                .build();
    }
}
