package com.example.bookstoreproject.api.book;

import com.example.bookstoreproject.domain.book.Book;
import lombok.experimental.UtilityClass;

import java.util.List;

import static org.apache.commons.collections4.ListUtils.emptyIfNull;

@UtilityClass
public class BookResponseDTOMapper {
    public static BookResponseDTO toBookResponseDTO(final Book book) {
        return BookResponseDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .description(book.getDescription())
                .createAt(book.getCreateAt())
                .updateAt(book.getUpdateAt())
                .image(book.getImage())
                .userId(book.getUserId())
                .build();
    }

    public static List<BookResponseDTO> toBookResponseDTOs(final List<Book> books) {
        return emptyIfNull(books)
                .stream()
                .map(BookResponseDTOMapper::toBookResponseDTO)
                .toList();
    }
}
