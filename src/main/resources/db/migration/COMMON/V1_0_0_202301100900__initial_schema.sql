CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

Create type role_name AS ENUM ('Admin', 'Contributor');

CREATE TABLE roles
(
    id   UUID         NOT NULL PRIMARY KEY DEFAULT uuid_generate_v4(),
    name role_name
);


