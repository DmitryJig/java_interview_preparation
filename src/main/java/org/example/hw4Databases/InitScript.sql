--Скрипты инициализации для бд Postgres

CREATE TABLE IF NOT EXISTS movies(
    id BIGSERIAL PRIMARY KEY,
    movie_name VARCHAR(255),
    length INT NOT NULL);

CREATE TABLE IF NOT EXISTS seances (
      id BIGSERIAL PRIMARY KEY,
      movie_id BIGINT NOT NULL REFERENCES movies (id),
      start_time TIMESTAMP NOT NULL,
      price NUMERIC(12, 2) NOT NULL);

CREATE TABLE IF NOT EXISTS tickets (
      id BIGSERIAL PRIMARY KEY,
      seance_id BIGINT NOT NULL REFERENCES seances (id));

INSERT INTO movies (movie_name, length)
        VALUES
        ('Titanic', 120),
        ('Fiksiki', 60),
        ('Mumii troll', 90),
        ('Blade', 90),
        ('Taxi', 90);

INSERT INTO seances (movie_id, start_time, price)
        VALUES
        (1, '2023-05-14 12:00:00', 500),
        (1, '2023-05-14 13:00:00', 500),
        (2, '2023-05-14 14:00:00', 500),
        (5, '2023-05-14 16:00:00', 500),
        (3, '2023-05-14 19:00:00', 500);

INSERT INTO tickets (seance_id)
VALUES
(1),
(2),
(3),
(4),
(5);