CREATE TABLE scopes(
    scope_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    scope_code VARCHAR(100) DEFAULT NULL,
    scope_name VARCHAR(256) DEFAULT NULL,
    search VARCHAR(512) DEFAULT NULL,
    status_code VARCHAR(100) DEFAULT NULL,
    status_name VARCHAR(256) DEFAULT NULL,
    created_by VARCHAR(100) DEFAULT NULL,
    updated_by VARCHAR(100) DEFAULT NULL,
    created_at DATETIME DEFAULT NULL,
    updated_at DATETIME DEFAULT NULL
);

CREATE INDEX idx_scope_code ON scopes(scope_code);