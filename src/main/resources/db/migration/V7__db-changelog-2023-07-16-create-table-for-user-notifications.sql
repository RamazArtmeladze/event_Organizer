--Flyway formatted sql

--changeset Ramaz Artmeladze:1
CREATE TABLE user_notification (
    id SERIAL PRIMARY KEY,
    event_id BIGINT,
    user_id BIGINT,
    notification VARCHAR(255)
);