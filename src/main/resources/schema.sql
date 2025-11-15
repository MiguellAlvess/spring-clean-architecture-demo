CREATE TABLE IF NOT EXISTS account (
    id bigint AUTO_INCREMENT primary key,
    name varchar(255) not null,
    password varchar(255) not null,
    email varchar(255) not null
)