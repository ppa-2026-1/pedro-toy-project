-- SQLite
CREATE TABLE IF NOT EXISTS users (
    id         INTEGER      PRIMARY KEY AUTOINCREMENT,
    handle     VARCHAR(255) UNIQUE NOT NULL,
    email      VARCHAR(255) UNIQUE NOT NULL,
    password   VARCHAR(255)        NOT NULL,
    created_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS roles (
    id   INTEGER      PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS users_roles (
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE IF NOT EXISTS profiles (
    id      INTEGER       PRIMARY KEY AUTOINCREMENT,
    name    VARCHAR(255),
    company VARCHAR(255),
    type    VARCHAR(255),
    FOREIGN KEY (id) REFERENCES users(id)
);


CREATE TABLE IF NOT EXISTS tickets (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    acao VARCHAR(255) NOT NULL,
    objeto VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL CHECK (status IN ('PENDENTE', 'ANDAMENTO', 'RESOLVIDO', 'CANCELADO')),
    detalhes TEXT,
    motivo TEXT,
    observadores TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    criador_id INT NOT NULL,
    destinatario_id INT NOT NULL,
    responsavel_id INT,
    FOREIGN KEY(criador_id) REFERENCES users(id),
    FOREIGN KEY(destinatario_id) REFERENCES users(id),
    FOREIGN KEY(responsavel_id) REFERENCES users(id)
);
