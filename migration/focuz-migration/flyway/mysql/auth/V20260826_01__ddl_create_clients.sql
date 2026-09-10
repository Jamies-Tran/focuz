CREATE TABLE clients(
    client_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    client_code VARCHAR(100) NOT NULL UNIQUE ,
    client_secret VARCHAR(251) NOT NULL UNIQUE ,
    redirect_uri VARCHAR(100) NOT NULL,
    search VARCHAR(512) DEFAULT NULL,
    created_by VARCHAR(100) DEFAULT NULL,
    updated_by VARCHAR(100) DEFAULT NULL,
    created_at DATETIME DEFAULT NULL,
    updated_at DATETIME DEFAULT NULL
);

CREATE INDEX idx_client_code ON clients(client_code);
CREATE INDEX idx_client_secret ON clients(client_secret);