CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE roles
(
    id serial    NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE users
(
    id   uuid         NOT NULL PRIMARY KEY DEFAULT uuid_generate_v4(),
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    firstName VARCHAR(100),
    lastName VARCHAR(100),
    avatar bytea,
    role_id serial NOT NULL,
    CONSTRAINT fk_roleId
        FOREIGN KEY(role_id)
            REFERENCES roles(id)
);

CREATE TABLE books
(
    id   uuid         NOT NULL PRIMARY KEY DEFAULT uuid_generate_v4(),
    title VARCHAR(255) NOT NULL,
    author VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    create_at timestamp NOT NULL,
    update_at timestamp NOT NULL,
    image bytea,
    user_id uuid,
    CONSTRAINT fk_userId
        FOREIGN KEY(user_id)
            REFERENCES users(id)
)

