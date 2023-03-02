CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

Create type role_name AS ENUM ('Admin', 'Contributor');

CREATE TABLE roles
(
    id   uuid         NOT NULL PRIMARY KEY DEFAULT uuid_generate_v4(),
    name role_name
);

CREATE TABLE users
(
    id   uuid         NOT NULL PRIMARY KEY DEFAULT uuid_generate_v4(),
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    firstName VARCHAR(100),
    lastName VARCHAR(100),
    avatar bytea,
    role_id uuid,
    CONSTRAINT fk_roleId
        FOREIGN KEY(role_id)
            REFERENCES roles(id)
);

