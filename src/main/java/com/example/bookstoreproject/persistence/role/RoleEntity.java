package com.example.bookstoreproject.persistence.role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Table(name = "roles")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RoleEntity {

    @Id
    private UUID id;
    private String name;
}
