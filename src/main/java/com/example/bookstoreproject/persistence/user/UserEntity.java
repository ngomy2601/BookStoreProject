package com.example.bookstoreproject.persistence.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Table(name = "users")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserEntity {

    @Id
    private UUID id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String avatar;
    private UUID roleId;

}
