package com.example.bookstoreproject.api.book;

import com.example.bookstoreproject.domain.book.Book;
import lombok.experimental.UtilityClass;

import java.util.List;

import static org.apache.commons.collections4.ListUtils.emptyIfNull;

@UtilityClass
public class BookRequestDTOMapper {
    public static BookRequestDTO toBookRequestDTO(final Book book) {
        return BookRequestDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .description(book.getDescription())
                .image(book.getImage())
                .build();
    }

    public static List<BookRequestDTO> toBookRequestDTOs(final List<Book> books) {
        return emptyIfNull(books)
                .stream()
                .map(BookRequestDTOMapper::toBookRequestDTO)
                .toList();
    }
}
