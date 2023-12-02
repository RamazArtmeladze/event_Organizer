--Flyway formatted sql

--changeset Ramaz Artmeladze:1
CREATE TABLE participant (
    event_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    PRIMARY KEY (event_id, user_id),
    FOREIGN KEY (event_id) REFERENCES event_model (event_id),
    FOREIGN KEY (user_id) REFERENCES user_model (user_id)
);