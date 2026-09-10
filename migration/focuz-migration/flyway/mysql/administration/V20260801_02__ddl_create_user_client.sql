CREATE TABLE user_client(
    user_client_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    client_id BIGINT,
    search VARCHAR(512) DEFAULT NULL,
    created_by VARCHAR(100) DEFAULT NULL,
    updated_by VARCHAR(100) DEFAULT NULL,
    created_at DATETIME DEFAULT NULL,
    updated_at DATETIME DEFAULT NULL
);

CREATE INDEX idx_user_id_client_id ON user_client(user_id, client_id);