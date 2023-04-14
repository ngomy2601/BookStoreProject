ALTER TABLE Users
    ALTER COLUMN password TYPE VARCHAR(1024);

UPDATE Users
SET password = '$2a$10$v.ZU2DqtVYE9GmiFx0540e7IsWmqi4IJr5MhM7NkoVM/Pi4gndqBu'
WHERE username = 'admin01';

INSERT INTO users (id, username, password, first_name, last_name, avatar, role_id)
VALUES ('6302a41b-e61f-40a9-9dd0-9eb10028a749', 'CRON_JOB',
        '$2a$12$3GjmcPUu7PpwwBreqaD4quYa.ZzHOjHtI7ZFbWV.i9MVwfIUTuusS',
        'CRON', 'JOB', 'avatar4.jpg',
        (SELECT id FROM roles WHERE name = 'CONTRIBUTOR'));
