CREATE TABLE user_infos(
    user_info_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL UNIQUE ,
    first_name VARCHAR(512) DEFAULT NULL,
    last_name VARCHAR(512) DEFAULT NULL,
    phone VARCHAR(50) DEFAULT NULL,
    mail VARCHAR(100) DEFAULT NULL,
    address VARCHAR(100) DEFAULT NULL,
    dob DATE DEFAULT NULL,
    intents_search VARCHAR(300) DEFAULT NULL,
    chars_search VARCHAR(300) DEFAULT NULL,
    created_by VARCHAR(100) DEFAULT NULL,
    updated_by VARCHAR(100) DEFAULT NULL,
    created_at DATETIME DEFAULT NULL,
    updated_at DATETIME DEFAULT NULL
);

CREATE UNIQUE INDEX idx_user_id ON user_infos (user_id);
CREATE INDEX idx_filter ON user_infos(intents_search, chars_search);