create database TASK_TRACKER;

create table users
(
    id         serial  PRIMARY KEY,
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    email VARCHAR(50) UNIQUE,
    password VARCHAR NOT NULL DEFAULT '123',
    status VARCHAR(10)
);
