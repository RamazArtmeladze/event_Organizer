--Flyway formatted sql

--changeset Ramaz Artmeladze:1
create table user_model (
      user_id SERIAL PRIMARY KEY,
      email VARCHAR(255) NOT NULL,
      first_name VARCHAR(255) NOT NULL,
      last_name VARCHAR(255) NOT NULL,
      password VARCHAR(255) NOT NULL
);