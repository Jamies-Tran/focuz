CREATE TABLE auth_groups(
    auth_group_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    auth_group_code VARCHAR(100) DEFAULT NULL,
    auth_group_name VARCHAR(256) DEFAULT NULL,
    intents_search VARCHAR(300) DEFAULT NULL,
    chars_search VARCHAR(300) DEFAULT NULL,
    created_by VARCHAR(100) DEFAULT NULL,
    updated_by VARCHAR(100) DEFAULT NULL,
    created_at DATETIME DEFAULT NULL,
    updated_at DATETIME DEFAULT NULL
);

CREATE UNIQUE INDEX idx_group_code ON auth_groups (auth_group_code);
CREATE INDEX idx_filter ON auth_groups (intents_search, chars_search);