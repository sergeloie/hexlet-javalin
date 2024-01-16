drop table if exists courses;

create table courses (
    id int primary key auto_increment,
    name varchar(255) not null,
    description text
);

drop table if exists users;

create table users (
    id int primary key auto_increment,
    name varchar(255) not null,
    email varchar(255) not null,
    password varchar(255) not null
);