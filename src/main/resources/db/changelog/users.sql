--liquibase formatted sql
--changeset Victor Frolov-Bukanov:8

CREATE TABLE users
(
    id       SERIAL PRIMARY KEY NOT NULL,
    username VARCHAR(255)       NOT NULL,
    password VARCHAR(255)       NOT NULL,
    type     VARCHAR(15)        NOT NULL
);

