CREATE TABLE permissions(
   permission_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    permission_code VARCHAR(100) DEFAULT NULL,
    permission_name VARCHAR(256) DEFAULT NULL,
   search VARCHAR(512) DEFAULT NULL,
    created_by VARCHAR(100) DEFAULT NULL,
    updated_by VARCHAR(100) DEFAULT NULL,
    created_at DATETIME DEFAULT NULL,
    updated_at DATETIME DEFAULT NULL
);

CREATE UNIQUE INDEX idx_permission_code ON permissions (permission_code);