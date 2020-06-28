create database TASK_TRACKER;

create table users
(
    id         serial PRIMARY KEY,
    first_name VARCHAR(30),
    last_name  VARCHAR(30),
    email      VARCHAR(50) UNIQUE,
    password   VARCHAR NOT NULL DEFAULT '123',
    status     VARCHAR(10)
);

create table tasks
(
    id          serial PRIMARY KEY,
    title       VARCHAR(30),
    description text,
    status      VARCHAR(11),
    user_id     int,
    foreign key (user_id) references users (id)
);