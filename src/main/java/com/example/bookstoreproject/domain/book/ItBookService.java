package com.example.bookstoreproject.domain.book;

import com.example.bookstoreproject.integration.BookApiAdapter;
import com.example.bookstoreproject.integration.ItBookDetailDTO;
import com.example.bookstoreproject.integration.ItBookItemDTO;
import com.example.bookstoreproject.persistence.book.BookStore;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static com.example.bookstoreproject.integration.ItBookMapper.toBook;
import static java.util.stream.Collectors.toSet;


@Service
@RequiredArgsConstructor
public class ItBookService {

    private final BookApiAdapter bookApiAdapter;
    private final BookStore bookStore;
    @Value("${user-id-cron-job}")
    private UUID userIdCronJob;

    public void storeNewBooks() {
        final List<ItBookItemDTO> newBooks = bookApiAdapter.fetchNewBooks();
        final List<ItBookItemDTO> booksToInsert = filterNewBooks(newBooks);

        booksToInsert.stream()
                .map(ItBookItemDTO::getIsbn13)
                .map(bookApiAdapter::fetchBookDetail)
                .forEach(this::save);
    }

    private List<ItBookItemDTO> filterNewBooks(final List<ItBookItemDTO> newBooks) {
        final Set<String> existingIsbn13s = bookStore.findAll().stream()
                .map(Book::getIsbn13)
                .collect(toSet());

        return newBooks.stream()
                .filter(book -> !existingIsbn13s.contains(book.getIsbn13()))
                .toList();
    }

    private void save(final ItBookDetailDTO itBookDetailDTO) {
        final Book book = toBook(itBookDetailDTO);

        book.setUserId(userIdCronJob);
        book.setCreateAt(Instant.now());

        bookStore.create(book);
    }
}
