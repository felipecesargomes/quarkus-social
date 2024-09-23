CREATE DATABASE quarkus-social;

CREATE TABLE "users"
(
    id bigserial NOT NULL PRIMARY KEY,
    name varchar(100) NOT NULL,
    age integer NOT NULL
);


CREATE TABLE "post" (
    id bigserial not null primary key,
    post_text varchar(150) not null,
    dateTime timestamp not null,
    user_id bigint not null references USERS(id)
);

CREATE TABLE "followers" (
    id bigserial not null primary key,
    user_id bigint not null references USERS(id),
    follower_id bigint not null references USERS(id)
);