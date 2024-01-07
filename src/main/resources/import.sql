CREATE SCHEMA IF NOT EXISTS gemstar;

CREATE TABLE gemstar.person (
    id        UUID NOT NULL,
    firstName VARCHAR(50),
    lastName  VARCHAR(50),
    email     VARCHAR(100),
    age       INTEGER NOT NULL,
    CONSTRAINT pk_person PRIMARY KEY (id)
);

ALTER TABLE gemstar.person ADD CONSTRAINT uc_person_email UNIQUE (email);