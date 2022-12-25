DROP TABLE IF EXISTS book_entity;

CREATE TABLE book_entity
(
    id bigint NOT NULL AUTO_INCREMENT,
    name varchar(50),
    price int,
    primary key(id)
);

insert into book_entity (name, price) values ('코틀린 완벽 가이드', 34000)