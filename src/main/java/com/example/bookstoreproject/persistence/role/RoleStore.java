package com.example.bookstoreproject.persistence.role;

import com.example.bookstoreproject.domain.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import static com.example.bookstoreproject.persistence.role.RoleEntityMapper.toRole;

@Repository
@RequiredArgsConstructor
public class RoleStore {
    private final RoleRepository roleRepository;

    public Role findById(final UUID id) {
        return toRole(roleRepository.findById(id).orElseThrow(null));
    }
}
