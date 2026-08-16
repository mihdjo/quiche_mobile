CREATE DATABASE IF NOT EXISTS quiche_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE quiche_db;

CREATE TABLE Korisnik (
    idKorisnik INT AUTO_INCREMENT PRIMARY KEY,
    ime VARCHAR(100) NOT NULL,
    prezime VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    datumRodjenja DATE NOT NULL
);

CREATE TABLE TipKuhinje (
    idTipKuhinje INT AUTO_INCREMENT PRIMARY KEY,
    tip VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE Recept (
    idRecept INT AUTO_INCREMENT PRIMARY KEY,
    naziv VARCHAR(150) NOT NULL,
    opis TEXT NOT NULL,
    napomena TEXT,
    datumKreiranja DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    idTipKuhinje INT NOT NULL,
    idKorisnik INT NOT NULL,

    CONSTRAINT fk_recept_tipkuhinje
        FOREIGN KEY (idTipKuhinje)
        REFERENCES TipKuhinje(idTipKuhinje),

    CONSTRAINT fk_recept_korisnik
        FOREIGN KEY (idKorisnik)
        REFERENCES Korisnik(idKorisnik)
);

CREATE TABLE Sastojak (
    idSastojak INT AUTO_INCREMENT PRIMARY KEY,
    naziv VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE ReceptSastojak (
    idRecept INT NOT NULL,
    idSastojak INT NOT NULL,
    kolicina DECIMAL(10,2) NOT NULL,
    jedinicaMere VARCHAR(50) NOT NULL,

    PRIMARY KEY (idRecept, idSastojak),

    CONSTRAINT fk_receptsastojak_recept
        FOREIGN KEY (idRecept)
        REFERENCES Recept(idRecept)
        ON DELETE CASCADE,

    CONSTRAINT fk_receptsastojak_sastojak
        FOREIGN KEY (idSastojak)
        REFERENCES Sastojak(idSastojak)
);

CREATE TABLE SacuvaniRecept (
    idKorisnik INT NOT NULL,
    idRecept INT NOT NULL,

    PRIMARY KEY (idKorisnik, idRecept),

    CONSTRAINT fk_sacuvani_korisnik
        FOREIGN KEY (idKorisnik)
        REFERENCES Korisnik(idKorisnik)
        ON DELETE CASCADE,

    CONSTRAINT fk_sacuvani_recept
        FOREIGN KEY (idRecept)
        REFERENCES Recept(idRecept)
        ON DELETE CASCADE
);

CREATE TABLE Korpa (
    idKorisnik INT NOT NULL,
    idRecept INT NOT NULL,

    PRIMARY KEY (idKorisnik, idRecept),

    CONSTRAINT fk_korpa_korisnik
        FOREIGN KEY (idKorisnik)
        REFERENCES Korisnik(idKorisnik)
        ON DELETE CASCADE,

    CONSTRAINT fk_korpa_recept
        FOREIGN KEY (idRecept)
        REFERENCES Recept(idRecept)
        ON DELETE CASCADE
);