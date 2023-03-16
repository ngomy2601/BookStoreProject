package com.example.bookstoreproject.api.book;

import com.example.bookstoreproject.domain.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.example.bookstoreproject.api.book.BookDTOMapper.toBookDTO;
import static com.example.bookstoreproject.api.book.BookDTOMapper.toBookDTOs;
import static com.example.bookstoreproject.domain.book.BookDomainMapper.toBook;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<BookDTO> findAll() {
        return toBookDTOs(bookService.findAll());
    }

    @GetMapping("{id}")
    public BookDTO findById(@PathVariable UUID id) {
        return toBookDTO(bookService.findById(id));
    }

    @PostMapping
    public BookDTO create(final @RequestBody BookDTO bookDTO) {
        return toBookDTO(bookService.create(toBook(bookDTO)));
    }

    @PutMapping("{id}")
    public BookDTO update(final @PathVariable UUID id, final @RequestBody BookDTO bookDTO) {
        return toBookDTO(bookService.update(id, toBook(bookDTO)));
    }
    
    @DeleteMapping("{id}")
    public void delete(final @PathVariable UUID id) {
        bookService.delete(id);
    }
}
