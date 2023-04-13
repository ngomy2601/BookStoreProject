package com.example.bookstoreproject.persistence.role;

import com.example.bookstoreproject.domain.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

import static com.example.bookstoreproject.domain.role.RoleError.supplyRoleNotFound;
import static com.example.bookstoreproject.persistence.role.RoleEntityMapper.toRole;

@Repository
@RequiredArgsConstructor
public class RoleStore {
    private final RoleRepository roleRepository;

    public Optional<Role> findById(final UUID id) {
        return roleRepository.findById(id).map(RoleEntityMapper::toRole);
    }

    public Role findByName(final String name) {
        return toRole(roleRepository.findByName(name).orElseThrow(supplyRoleNotFound(name)));
    }
}
