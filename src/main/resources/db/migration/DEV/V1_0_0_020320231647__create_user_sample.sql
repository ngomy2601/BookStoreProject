INSERT INTO users (username, password, first_name, last_name, role_id)
VALUES ('thanh', '123456', 'ba', 'thanh', (SELECT id FROM roles WHERE name = 'CONTRIBUTOR'));