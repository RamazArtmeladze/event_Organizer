-- Flyway formatted SQL

-- changeset Ramaz Artmeladze:2
ALTER TABLE user_model
ADD CONSTRAINT UK_email UNIQUE (email);
