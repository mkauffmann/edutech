CREATE TABLE badges (
    ID SERIAL PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL,
    IMG_URL VARCHAR(255) NOT NULL
);


INSERT INTO badges (NAME, IMG_URL)
VALUES
    ('Régua', '/images/badges/ruler.png'),
    ('Livro', '/images/badges/book.png'),
    ('Medalha', '/images/badges/medal.png'),
    ('Formado', '/images/badges/mortarboard.png');
