package com.example.bookstoreproject.persistence.role;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends CrudRepository<RoleEntity, UUID> {
    Optional<RoleEntity> findByName(final String name);
}
