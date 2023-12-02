--Flyway formatted sql

--changeset Ramaz Artmeladze:1
CREATE TABLE event_model (
    event_id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    start_date DATE,
    end_date DATE,
    location VARCHAR(255),
    description VARCHAR(255),
    estimate_budget DECIMAL(10, 2),
    is_closed BOOLEAN,
    created_by BIGINT
);