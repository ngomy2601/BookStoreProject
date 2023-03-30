package com.example.bookstoreproject.domain.book;

import com.example.bookstoreproject.api.book.BookDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BookDomainMapper {
    public static Book toBook(final BookDTO bookDTO) {
        return Book.builder()
                .id(bookDTO.getId())
                .title(bookDTO.getTitle())
                .author(bookDTO.getAuthor())
                .description(bookDTO.getDescription())
                .image(bookDTO.getImage())
                .build();
    }
}
