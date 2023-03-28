package com.example.bookstoreproject.persistence.role;

import org.junit.jupiter.api.Test;

import static com.example.bookstoreproject.fakes.RoleFakes.buildRoleEntity;
import static com.example.bookstoreproject.persistence.role.RoleEntityMapper.toRole;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RoleEntityMapperTest {

    @Test
    void shouldToRole_OK() {
        final var roleEntity = buildRoleEntity();
        final var role = toRole(roleEntity);

        assertEquals(roleEntity.getId(), role.getId());
        assertEquals(roleEntity.getName(), role.getName());
    }
}