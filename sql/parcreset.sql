DROP DATABASE IF EXISTS parc_info;
CREATE DATABASE parc_info;
USE parc_info;

CREATE TABLE marque (
    id INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(50) NOT NULL
);

CREATE TABLE modele (
    id INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(50) NOT NULL,
    id_marque INT NOT NULL,
    CONSTRAINT fk_modele_marque FOREIGN KEY (id_marque) REFERENCES marque(id)
);

CREATE TABLE ordinateur (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_modele INT NOT NULL,
    processeur VARCHAR(50) NOT NULL,
    ram INT NOT NULL,
    disque_dur INT NOT NULL,
    CONSTRAINT fk_ordinateur_modele FOREIGN KEY (id_modele) REFERENCES modele(id)
);

CREATE TABLE table_utilisateur (
    id INT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(50) NOT NULL,
    mot_de_passe VARCHAR(50) NOT NULL,
    role VARCHAR(20) NOT NULL
);

CREATE TABLE etat (
    id INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(50) NOT NULL
);

CREATE TABLE etatpanne (
    id INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(50) NOT NULL
);

CREATE TABLE ordinateur_etat (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_ordi INT NOT NULL,
    id_etat INT NOT NULL,
    id_panne INT,
    date DATE NOT NULL,
    observation VARCHAR(255),
    CONSTRAINT fk_ordinateuretat_ordi FOREIGN KEY (id_ordi) REFERENCES ordinateur(id),
    CONSTRAINT fk_ordinateuretat_etat FOREIGN KEY (id_etat) REFERENCES etat(id),
    CONSTRAINT fk_ordinateuretat_panne FOREIGN KEY (id_panne) REFERENCES etatpanne(id)
);


INSERT INTO marque (libelle) VALUES ('Dell'), ('HP'), ('Lenovo'), ('HP'), ('Dell'), ('Dell'), ('Lenovo'), ('Acer');

INSERT INTO modele (libelle, id_marque) VALUES
('Optiplex', 1),
('Prodesk', 2),
('ThinkCentre', 3),
('ProBook', 4),
('Latitude', 5),
('Precision', 6),
('ThinkCentre', 7),
('Veriton', 8);

INSERT INTO ordinateur (id_modele, processeur, ram, disque_dur) VALUES
(1, 'Intel Core i7', 16, 512),
(2, 'Intel Core i7', 8, 256),
(3, 'Intel Core i5', 8, 512),
(4, 'Intel Core i9', 16, 512),
(5, 'Intel Core i5', 16, 512),
(6, 'Intel Core i7', 16, 256),
(7, 'Intel Core i9', 8, 256),
(8, 'Intel Core i7', 8, 256);

INSERT INTO table_utilisateur (login, mot_de_passe, role) VALUES
('Sunset Shimmer', 'sundaysunset', 'utilisateur');

INSERT INTO etat (libelle) VALUES
('Fonctionnel'),
('Non fonctionnel');

INSERT INTO etatpanne (libelle) VALUES
('Alimentation'),
('Disque Dur'),
('Carte mere');

INSERT INTO ordinateur_etat (id_ordi, id_etat, id_panne, date, observation) VALUES
(1, 1, null, '2026-07-21', 'Nety tsara'),
(1, 2, 3, '2026-07-22', 'tsy nandeha tampoka'),
(1, 1, null, '2026-07-23', 'zay vao nety'),
(2, 1, null, '2026-07-22', 'milamina'),
(2, 2, 1, '2026-07-23', 'tsy milamina'),
(3, 2, 1, '2026-07-21', 'tsy nandeha'),
(3, 1, null, '2026-07-22', 'nandeha'),
(3, 2, 2, '2026-07-23', 'tsy nandeha'),
(4, 1, null, '2026-07-23', 'tsy misy kianina'),
(5, 1, null, '2026-07-21', 'mandeha tsara'),
(5, 2, 3, '2026-07-23', 'tsy hay hoe ahoana'),
(6, 2, 1, '2026-07-23', 'tsy mety velona mihintsy'),
(7, 1, null, '2026-07-21', 'nandeha'),
(7, 2, 2, '2026-07-23', 'maty tampoka'),
(8, 1, null, '2026-07-23', 'milamina tsara');