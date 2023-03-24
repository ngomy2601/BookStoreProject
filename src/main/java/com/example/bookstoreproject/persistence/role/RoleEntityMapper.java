package com.example.bookstoreproject.persistence.role;

import com.example.bookstoreproject.domain.role.Role;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RoleEntityMapper {

    public static Role toRole(final RoleEntity roleEntity) {
        return Role.builder()
                .id(roleEntity.getId())
                .name(roleEntity.getName())
                .build();
    }
}
