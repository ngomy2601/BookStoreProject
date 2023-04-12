package com.example.bookstoreproject.persistence.role;

import com.example.bookstoreproject.domain.role.Role;
import com.example.bookstoreproject.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class RoleStore {
    private final RoleRepository roleRepository;

    public Optional<Role> findById(final UUID id) {
        return roleRepository.findById(id).map(RoleEntityMapper::toRole);
    }

    public UUID findIdByName(final String roleName) {
        return roleRepository.findByName(roleName)
                .map(RoleEntity::getId)
                .orElseThrow(() -> new NotFoundException("Role not found - " + roleName));
    }
}
