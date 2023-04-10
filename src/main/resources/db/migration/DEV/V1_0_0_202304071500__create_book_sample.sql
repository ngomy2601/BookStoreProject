INSERT INTO books (title, author, description, create_at, update_at, user_id)
VALUES ('Harry Potter', 'J. K. Rowling',
        'Harry Potter is a seven-part series of paranormal novels by English writer J. K. Rowling',
        '2023-03-02 12:00:00', '2023-03-02 12:00:00',
        (SELECT id FROM users WHERE username = 'admin01'));
INSERT INTO books (title, author, description, create_at, update_at, user_id)
VALUES ('Harry Potter2', 'J. K. Rowling', 'Writer J. K. Rowling',
        '2023-03-02 12:02:00', '2023-03-02 12:02:00',
        (SELECT id FROM users WHERE username = 'admin01'));