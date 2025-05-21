-- Table CO2 nettoyée
CREATE TABLE IF NOT EXISTS clean_co2 (
    id SERIAL PRIMARY KEY,
    modele VARCHAR(255),
    bonus_malus NUMERIC
);

-- Table Catalogue nettoyée
CREATE TABLE IF NOT EXISTS clean_catalogue (
    id SERIAL PRIMARY KEY,
    marque VARCHAR(100),
    nom VARCHAR(100),
    puissance INT,
    longueur VARCHAR(50),
    nb_places INT,
    nb_portes INT,
    couleur VARCHAR(50),
    occasion BOOLEAN,
    prix NUMERIC
);

-- Table Clients (import Mongo -> HDFS -> PostgreSQL
CREATE TABLE IF NOT EXISTS clean_clients (
    id SERIAL PRIMARY KEY,
    name TEXT,
    age INT,
    city TEXT
);
