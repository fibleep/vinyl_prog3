INSERT INTO AUTHOR(name, age, gender)
VALUES ('Taco Hemingway', 32, 'MALE'),
       ('Dawid Podsiadło', 29, 'MALE'),
       ('Daria Zawiałow', 30, 'FEMALE'),
       ('Quebonafide', 31, 'MALE');

INSERT INTO ALBUM(title, release_year)
VALUES ('Wojny i Noce', 2021),
       ('POCZTÓWKA Z WWA, LATO ''19', 2019),
       ('Wosk', 2016),
       ('Małomiasteczkowy', 2018),
       ('ROMANTICPSYCHO', 2020);
INSERT INTO SONG(title, duration, album_index)
VALUES
    -- WOJNY I NOCE
    ('Za krótki sen', 4.01, 4),
    ('Flower Night', 4.27, 12),
    ('Serce', 3.49, 11),
    ('Hollow', 3.54, 10),
    -- POCZTÓWKA Z WWA LATO '19
    ('W PIĄTKI LEŻĘ W WANNIE', 3.48, 4),
    ('LECI NOWY FUTURE', 3.39, 3),
    ('SANATORIUM', 4.34, 9),
    ('WWA VHS', 4.41, 5),
    -- WOSK
    ('Wosk', 3.53, 1),
    ('BXL', 3.52, 2),
    ('Szczerze', 3.21, 3),
    ('Wiatr', 3.42, 4),
    -- MAŁOMIASTECZKOWY
    ('Małomiasteczkowy', 3.23, 3),
    ('Najnowszy klip', 3.49, 4),
    ('Trofea', 3.44, 5),
    ('Nie ma fal', 3.58, 6),
    -- ROMANTICPSYCHO
    ('ROMANTICPSYCHO', 3.36, 1),
    ('JESIEŃ', 3.32, 2),
    ('TOKYO2020', 3.32, 12),
    ('BUBBLETEA', 4.44, 15);

INSERT INTO ENTRY(author_id, song_id, album_id, co_author)
VALUES
    -- DARIA ZAWIAŁOW
    -- WOJNY I NOCE
    (3, 1, 1, FALSE),
    (3, 2, 1, FALSE),
    (3, 3, 1, FALSE),
    (3, 4, 1, FALSE),
    -- BUBBLETEA
    (2, 20, 5, TRUE),

    -- TACO HEMINGWAY
    -- WOSK
    (1, 5, 3, FALSE),
    (1, 6, 3, FALSE),
    (1, 7, 3, FALSE),
    (1, 8, 3, FALSE),
    -- POCZTÓWKA Z WWA LATO '19
    (1, 9, 2, FALSE),
    (1, 10, 2, FALSE),
    (1, 11, 2, FALSE),
    (1, 12, 2, FALSE),
    -- DAWID PODSIADŁO
    -- MAŁOMIASTECZKOWY
    (2, 13, 4, FALSE),
    (2, 14, 4, FALSE),
    (2, 15, 4, FALSE),
    (2, 16, 4, FALSE),
    -- ZA KRÓTKI SEN
    (2, 1, 1, TRUE),
    -- W PIĄTKI LEŻĘ W WANNIE
    (2, 9, 2, TRUE),
    -- QUEBONAFIDE
    (4, 17, 5, FALSE),
    (4, 18, 5, FALSE),
    (4, 19, 5, FALSE),
    (4, 20, 5, FALSE);