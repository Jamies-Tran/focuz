CREATE TABLE group_permission(
    group_permission_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    group_id BIGINT,
    permission_id BIGINT,
    search VARCHAR(512) DEFAULT NULL,
    created_by VARCHAR(100) DEFAULT NULL,
    updated_by VARCHAR(100) DEFAULT NULL,
    created_at DATETIME DEFAULT NULL,
    updated_at DATETIME DEFAULT NULL
);

CREATE INDEX idx_group_id_permission_id ON group_permission(group_id, permission_id);