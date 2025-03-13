CREATE TABLE IF NOT EXISTS roles(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS users (
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    enabled BOOLEAN,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

# On Utilise toujours des liste de roles, pour permettre l'ajout de nouveaux roles a un utilisateur
CREATE TABLE IF NOT EXISTS users_roles (
    users_id INT,
    roles_id INT,
    FOREIGN KEY (users_id) REFERENCES users(id),
    FOREIGN KEY (roles_id) REFERENCES roles(id),
    PRIMARY KEY (users_id, roles_id)
);

# Le nom des roles doit commencer par ROLE_ en spring security
INSERT INTO roles (name) VALUES
('ROLE_ADMIN'),
('ROLE_USER'),
('ROLE_SCRAPER');

CREATE TABLE IF NOT EXISTS irish_breweries (
     id CHAR(36) PRIMARY KEY,
     name VARCHAR(255) NOT NULL,
     brewery_type VARCHAR(255) NOT NULL,
     address_1 VARCHAR(255),
     city VARCHAR(255) NOT NULL,
     state_province VARCHAR(255) NOT NULL,
     postal_code VARCHAR(255) NOT NULL,
     country VARCHAR(255) NOT NULL,
     website_url VARCHAR(255),
     phone VARCHAR(255)
);
