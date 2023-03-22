package com.example.bookstoreproject.persistence.role;

import com.example.bookstoreproject.domain.role.Role;
import lombok.experimental.UtilityClass;

import java.util.List;

import static org.apache.commons.collections4.ListUtils.emptyIfNull;

@UtilityClass
public class RoleEntityMapper {

    public static Role toRole(final RoleEntity roleEntity) {
        return Role.builder()
                .id(roleEntity.getId())
                .name(roleEntity.getName())
                .build();
    }

    public static List<Role> toRoles(final List<RoleEntity> roleEntities) {
        return emptyIfNull(roleEntities)
                .stream()
                .map(RoleEntityMapper::toRole)
                .toList();
    }

    public static RoleEntity toRoleEntity(final Role role) {
        return RoleEntity.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }
}
