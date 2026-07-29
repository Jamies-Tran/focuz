CREATE TABLE users(
    user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(250) DEFAULT NULL UNIQUE,
    password VARCHAR(100) DEFAULT NULL,
    intents_search VARCHAR(300) DEFAULT NULL,
    chars_search VARCHAR(300) DEFAULT NULL,
    created_by VARCHAR(100) DEFAULT NULL,
    updated_by VARCHAR(100) DEFAULT NULL,
    created_at DATETIME DEFAULT NULL,
    updated_at DATETIME DEFAULT NULL
);

CREATE UNIQUE INDEX idx_username ON users (username);
CREATE INDEX idx_filter ON users(intents_search, chars_search);