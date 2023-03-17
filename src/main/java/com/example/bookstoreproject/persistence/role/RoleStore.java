package com.example.bookstoreproject.persistence.role;

import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class RoleStore {

    public String getRoleById(final UUID uuid) {
        if(uuid.equals(UUID.fromString("8720c2d6-f7d2-4ada-b0da-ee62a7640c6d"))) {
            return "ROLE_ADMIN";
        }

        if(uuid.equals(UUID.fromString("7f146aeb-49cf-4e5c-ae27-7d8a290764d2"))) {
            return "ROLE_USER";
        }

        return null;
    }
}
