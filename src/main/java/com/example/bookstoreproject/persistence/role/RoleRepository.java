package com.example.bookstoreproject.persistence.role;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, UUID> {

 @Query("SELECT c.name from RoleEntity c WHERE c.id = :roleId  ")
    String findRoleName(final UUID roleId);

    Optional<RoleEntity> findByName(final String roleName);
}
