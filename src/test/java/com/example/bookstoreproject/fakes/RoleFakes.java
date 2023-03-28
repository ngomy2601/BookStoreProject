package com.example.bookstoreproject.fakes;

import com.example.bookstoreproject.domain.role.Role;
import com.example.bookstoreproject.persistence.role.RoleEntity;

import java.util.UUID;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class RoleFakes {

    public static RoleEntity buildRoleEntity() {
        return RoleEntity.builder()
                .id(UUID.randomUUID())
                .name(randomAlphabetic(3, 10))
                .build();
    }

    public static Role buildRole() {
        return Role.builder()
                .id(UUID.randomUUID())
                .name(randomAlphabetic(3, 10))
                .build();
    }
}
