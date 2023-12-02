--Flyway formatted sql

--changeset Ramaz Artmeladze:1
CREATE TABLE expenses_model (
  expenses_id SERIAL PRIMARY KEY,
  amount DOUBLE PRECISION,
  description VARCHAR(255),
  created_by BIGINT,
  event_id BIGINT,
      FOREIGN KEY (event_id) REFERENCES event_model (event_id)
);

