package com.example.bookstoreproject.persistence.book;

import com.example.bookstoreproject.domain.book.Book;
import lombok.experimental.UtilityClass;

import java.util.List;

import static org.apache.commons.collections4.ListUtils.emptyIfNull;

@UtilityClass
public class BookEntityMapper {

    public static Book toBook(final BookEntity bookEntity){
        return Book.builder()
                .id(bookEntity.getId())
                .title(bookEntity.getTitle())
                .author(bookEntity.getAuthor())
                .description(bookEntity.getDescription())
                .createAt(bookEntity.getCreateAt())
                .updateAt(bookEntity.getUpdateAt())
                .image(bookEntity.getImage())
                .userId(bookEntity.getUserId())
                .build();
    }

    public static List<Book> toBooks(final List<BookEntity> bookEntities) {
        return emptyIfNull(bookEntities)
                .stream()
                .map(BookEntityMapper::toBook)
                .toList();
    }

    public static BookEntity toBookEntity(final Book book) {
        return BookEntity.builder()
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
}
