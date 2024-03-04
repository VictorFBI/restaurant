--liquibase formatted sql
--changeset Victor Frolov-Bukanov:4

CREATE TABLE users
(
    id       UUID PRIMARY KEY NOT NULL,
    username VARCHAR(255)     NOT NULL UNIQUE,
    password VARCHAR(255)     NOT NULL,
    type     VARCHAR(15)      NOT NULL
);