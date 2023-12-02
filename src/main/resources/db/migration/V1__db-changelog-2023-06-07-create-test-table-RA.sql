--Flyway formatted sql

--changeset Ramaz Artmeladze:1
create table test_model (
    ID SERIAL PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL
);