CREATE TABLE user_group(
    user_group_id BIGINT PRIMARY KEY AUTO_INCREMENT,
   user_id BIGINT,
    auth_group_id BIGINT,
    search VARCHAR(512) DEFAULT NULL,
    created_by VARCHAR(100) DEFAULT NULL,
    updated_by VARCHAR(100) DEFAULT NULL,
    created_at DATETIME DEFAULT NULL,
    updated_at DATETIME DEFAULT NULL
);

CREATE INDEX idx_user_id_group_id ON user_group (user_id, auth_group_id);