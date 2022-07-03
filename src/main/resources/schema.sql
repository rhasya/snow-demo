create sequence HIBERNATE_SEQUENCE;

create table USERS (
    id bigint primary key,
    username varchar(50) unique,
    password varchar(500) not null,
    enabled boolean not null
);

create table AUTHORITIES (
    username varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);

create table PROBLEM (
    id bigint primary key,
    title varchar(255),
    content varchar(5000)
);