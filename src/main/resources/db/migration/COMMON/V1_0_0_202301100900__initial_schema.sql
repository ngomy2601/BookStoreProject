CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE roles
(
    id   UUID        NOT NULL PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(50) NOT NULL
);

INSERT INTO roles(id, name)
VALUES ('8720c2d6-f7d2-4ada-b0da-ee62a7640c6d', 'ADMIN'),
       ('7f146aeb-49cf-4e5c-ae27-7d8a290764d2', 'CONTRIBUTOR');

CREATE TABLE users
(
    id         UUID        NOT NULL PRIMARY KEY DEFAULT uuid_generate_v4(),
    username   VARCHAR(50) NOT NULL,
    password   VARCHAR(50) NOT NULL,
    first_name VARCHAR(100),
    last_name  VARCHAR(100),
    avatar     VARCHAR(1024),
    role_id    UUID        NOT NULL,
    CONSTRAINT fk_roleId
        FOREIGN KEY (role_id)
            REFERENCES roles (id)
);

INSERT INTO users(id, username, password, first_name, last_name, role_id)
VALUES ('6adfa7f8-a69e-4b8a-aa6a-049d6eaf7afb', 'admin01', 'encrypted_password', 'My', 'Ngo',
        '8720c2d6-f7d2-4ada-b0da-ee62a7640c6d');

CREATE TABLE books
(
    id          UUID         NOT NULL PRIMARY KEY DEFAULT uuid_generate_v4(),
    title       VARCHAR(255) NOT NULL,
    author      VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    create_at   TIMESTAMP    NOT NULL,
    update_at   TIMESTAMP    NOT NULL,
    image       VARCHAR(1024),
    user_id     UUID,
    CONSTRAINT fk_userId
        FOREIGN KEY (user_id)
            REFERENCES users (id)
)

