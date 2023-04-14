package com.example.bookstoreproject.integration;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ItBooksResponseDTO {
    private List<ItBookItemDTO> books;
}
