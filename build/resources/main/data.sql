INSERT INTO roles (name) VALUES
    ('ROLE_USER'),
    ('ROLE_GUEST'),
    ('ROLE_VIEWER')
;

INSERT INTO users (handle, email, password, created_at)
VALUES 
    ('marcio', 'marcio@mail.com', 'password', CURRENT_TIMESTAMP),
    ('josue', 'josue@mail.com', 'password', CURRENT_TIMESTAMP)
;

INSERT INTO users_roles (user_id, role_id) 
VALUES
    (1, 1), -- Marcio has ROLE_USER
    (1, 3), -- Marcio has ROLE_VIEWER
    (2, 2)  -- Josue has ROLE_GUEST
;

INSERT INTO profiles (id, name, company, type)
VALUES
    (1, 'Marcio Ramos', 'Empresa 1', 'PROFESSIONAL'),
    (2, 'Josue Torres', 'Empresa 2', 'FREE')
;

-- RELACIONAL
INSERT INTO vulnerability_reports (system_under_test, created_at, updated_at, user_id) 
VALUES
    ('Sistema de Login', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('Portal do Cliente', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('API de Pagamentos', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
    ('Dashboard Admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('App Mobile', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
    ('Sistema de RH', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('E-commerce', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
    ('Sistema de Banco', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('Plataforma de Ensino', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
    ('CRM da Empresa', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('Sistema de Estoque', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
    ('Portal de Notícias', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('Sistema de Reservas', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
    ('API de Autenticação', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('Sistema de Faturamento', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
    ('Chat da Empresa', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('Sistema de Backup', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
    ('Monitoramento de Servidores', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('Sistema de Logs', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
    ('API de Relatórios', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1)
;

INSERT INTO vulnerabilities (description, severity, report_id, created_at, updated_at)
VALUES
        -- Relatório 1
    ('SQL Injection', 'HIGH', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('XSS Attack', 'MEDIUM', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    -- Relatório 2
    ('IDOR Vulnerability', 'HIGH', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('CSRF Missing', 'MEDIUM', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    -- Relatório 3
    ('Weak Password Policy', 'LOW', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    -- Relatório 4
    ('Buffer Overflow', 'CRITICAL', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    -- Relatório 5
    ('No Rate Limiting', 'MEDIUM', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Information Disclosure', 'HIGH', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    -- Relatório 6
    ('Broken Authentication', 'CRITICAL', 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    -- Relatório 7
    ('SSRF Vulnerability', 'HIGH', 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    -- Relatório 8
    ('XXE Injection', 'HIGH', 8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Log Forgery', 'LOW', 8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    -- Relatório 9
    ('Command Injection', 'CRITICAL', 9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    -- Relatório 10
    ('Path Traversal', 'HIGH', 10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Open Redirect', 'MEDIUM', 10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    -- Relatório 11
    ('Hardcoded Credentials', 'HIGH', 11, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    -- Relatório 12
    ('Insecure Deserialization', 'CRITICAL', 12, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    -- Relatório 13
    ('Missing Security Headers', 'LOW', 13, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    -- Relatório 14
    ('Weak Encryption', 'HIGH', 14, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    -- Relatório 15
    ('Clickjacking', 'MEDIUM', 15, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    -- Relatório 16
    ('Cache Poisoning', 'LOW', 16, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    -- Relatório 17
    ('DNS Rebinding', 'MEDIUM', 17, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    -- Relatório 18
    ('Race Condition', 'HIGH', 18, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    -- Relatório 19
    ('Insecure File Upload', 'HIGH', 19, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    -- Relatório 20
    ('CORS Misconfiguration', 'MEDIUM', 20, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Session Fixation', 'HIGH', 20, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
;