DROP TABLE IF EXISTS book_entity;

CREATE TABLE book_entity
(
    id bigint NOT NULL AUTO_INCREMENT,
    name varchar(50),
    price int,
    primary key(id)
);

DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id bigint NOT NULL AUTO_INCREMENT,
    email varchar(150),
    user_name varchar(100),
    profile_url varchar(300),
    password varchar(500),
    created_at date,
    updated_at date,
    primary key(id)
);
