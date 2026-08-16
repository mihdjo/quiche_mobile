USE quiche_db;

INSERT INTO Korisnik
    (ime, prezime, username, password, datumRodjenja)
VALUES
    ('Mihailo', 'Djokovic', 'mihailo', 'test123', '2000-05-15'),
    ('Ana', 'Jovanovic', 'ana', 'test123', '2001-08-22');

INSERT INTO TipKuhinje (tip)
VALUES
    ('Italijanska'),
    ('Francuska'),
    ('Srpska');

INSERT INTO Sastojak (naziv)
VALUES
    ('Jaja'),
    ('Slanina'),
    ('Sir'),
    ('Testenina'),
    ('Mleko');

INSERT INTO Recept
    (naziv, opis, napomena, idTipKuhinje, idKorisnik)
VALUES
    (
        'Carbonara',
        'Klasično italijansko jelo sa testeninom, jajima i slaninom.',
        'Poslužiti odmah nakon pripreme.',
        1,
        1
    );

INSERT INTO ReceptSastojak
    (idRecept, idSastojak, kolicina, jedinicaMere)
VALUES
    (1, 1, 3, 'kom'),
    (1, 2, 150, 'g'),
    (1, 3, 80, 'g'),
    (1, 4, 400, 'g');

INSERT INTO SacuvaniRecept
    (idKorisnik, idRecept)
VALUES
    (2, 1);

INSERT INTO Korpa
    (idKorisnik, idRecept)
VALUES
    (2, 1);