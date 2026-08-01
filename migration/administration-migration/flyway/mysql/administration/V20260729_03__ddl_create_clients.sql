CREATE TABLE clients(
    client_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    client_code VARCHAR(100) DEFAULT NULL UNIQUE,
    client_name VARCHAR(256) DEFAULT NULL,
    client_secret VARCHAR(100) DEFAULT  NULL,
    redirect_uri VARCHAR(256) DEFAULT NULL,
    search VARCHAR(512) DEFAULT NULL,
    created_by VARCHAR(100) DEFAULT NULL,
    updated_by VARCHAR(100) DEFAULT NULL,
    created_at DATETIME DEFAULT NULL,
    updated_at DATETIME DEFAULT NULL
);

CREATE UNIQUE INDEX idx_client_code ON clients (client_code);
CREATE UNIQUE INDEX idx_client_secret ON clients (client_secret);