CREATE TABLE applications(
    application_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    application_code VARCHAR(100) NOT NULL,
    application_name VARCHAR(256) NOT NULL,
    search VARCHAR(512) DEFAULT NULL,
    created_by VARCHAR(100) DEFAULT NULL,
    updated_by VARCHAR(100) DEFAULT NULL,
    created_at DATETIME DEFAULT NULL,
    updated_at DATETIME DEFAULT NULL
);

CREATE INDEX idx_application_code ON applications(application_code);