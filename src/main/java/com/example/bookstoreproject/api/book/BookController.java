package com.example.bookstoreproject.api.book;

import com.example.bookstoreproject.domain.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.example.bookstoreproject.api.book.BookResponseDTOMapper.toBookResponseDTO;
import static com.example.bookstoreproject.api.book.BookResponseDTOMapper.toBookResponseDTOs;
import static com.example.bookstoreproject.domain.book.BookDomainMapper.toBook;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<BookResponseDTO> findAll() {
        return toBookResponseDTOs(bookService.findAll());
    }

    @GetMapping("{id}")
    public BookResponseDTO findById(@PathVariable UUID id) {
        return toBookResponseDTO(bookService.findById(id));
    }

    @PostMapping
    public BookResponseDTO create(final @RequestBody BookRequestDTO bookDTO) {
        return toBookResponseDTO(bookService.create(toBook(bookDTO)));
    }

    @PutMapping("{id}")
    public BookResponseDTO update(final @PathVariable UUID id, final @RequestBody BookRequestDTO bookDTO) {
        return toBookResponseDTO(bookService.update(id, toBook(bookDTO)));
    }

    @DeleteMapping("{id}")
    public void delete(final @PathVariable UUID id) {
        bookService.delete(id);
    }

    @PostMapping("{id}/image")
    public void upload(final @PathVariable UUID id, @RequestParam("file") final MultipartFile file) throws IOException {
        bookService.uploadImage(id, file.getBytes());
    }
}
