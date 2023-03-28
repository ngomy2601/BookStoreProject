package com.example.bookstoreproject.domain.role;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.With;

import java.util.UUID;

@Getter
@Setter
@Builder
@With
public class Role {

    private UUID id;
    private String name;
}
