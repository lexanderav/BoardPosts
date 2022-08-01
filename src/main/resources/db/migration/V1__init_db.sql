create sequence posts_seq start 1 increment 1;
create sequence users_seq start 1 increment 1;
create table posts
(
    id       int8 not null,
    context  text,
    filename varchar(255),
    title    varchar(255),
    user_id  int8,
    primary key (id)
);
create table users
(
    id       int8 not null,
    email    varchar(255),
    name     varchar(255),
    password varchar(255),
    roles    varchar(255),
    primary key (id)
);
alter table if exists posts add constraint post_fk_users foreign key (user_id) references users;