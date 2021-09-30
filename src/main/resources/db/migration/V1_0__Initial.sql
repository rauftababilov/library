create sequence seq_book increment 10;
create sequence seq_user;
create sequence seq_author increment 10;
create sequence seq_publishing_house increment 10;
create sequence seq_book_audit increment 20;
create sequence seq_client increment 10;

create table "user"
(
    id         bigint
        constraint user_pk primary key,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp,
    username   text      not null,
    password   text      not null,
    deleted    boolean   not null default false,
    unique (username)
);

create table "role"
(
    id   bigint
        constraint role_pk primary key,
    name text not null,
    unique (name)
);

create table user_role
(
    role_id bigint not null
        constraint user_role_role_fk references "role",
    user_id bigint not null
        constraint user_role_user_fk references "user",
    constraint user_role_pk primary key (role_id, user_id)
);

create table publishing_house
(
    id         bigint
        constraint publishing_house_pk primary key,
    created_by bigint    not null
        constraint publishing_house_user_create_fk references "user",
    created_at timestamp not null default current_timestamp,
    updated_by bigint    not null
        constraint publishing_house_user_update_fk references "user",
    updated_at timestamp not null default current_timestamp,
    title      text      not null,
    deleted    boolean   not null default false,
    unique (title)
);

create table book
(
    id                  bigint
        constraint book_pk primary key,
    created_by          bigint    not null
        constraint book_user_create_fk references "user",
    created_at          timestamp not null default current_timestamp,
    updated_by          bigint    not null
        constraint book_user_update_fk references "user",
    updated_at          timestamp not null default current_timestamp,
    title               text      not null,
    publish_year        int       not null,
    deleted             boolean   not null default false,
    publishing_house_id bigint    not null
        constraint book_publishing_house_fk references publishing_house
);

create table author
(
    id         bigint
        constraint author_pk primary key,
    created_by bigint    not null
        constraint author_user_create_fk references "user",
    created_at timestamp not null default current_timestamp,
    updated_by bigint    not null
        constraint author_user_update_fk references "user",
    updated_at timestamp not null default current_timestamp,
    full_name  text      not null,
    deleted    boolean   not null default false
);

create table book_author
(
    book_id   bigint not null
        constraint book_author_book_fk references book,
    author_id bigint not null
        constraint book_author_author_fk references author,
    constraint book_author_pk primary key (book_id, author_id)
);

create table client
(
    id         bigint
        constraint client_pk primary key,
    created_by bigint    not null
        constraint client_user_create_fk references "user",
    created_at timestamp not null default current_timestamp,
    updated_by bigint    not null
        constraint client_user_update_fk references "user",
    updated_at timestamp not null default current_timestamp,
    full_name  text      not null,
    birthday   date      not null,
    deleted    boolean   not null default false,
    unique (full_name)
);

create table book_audit
(
    id         bigint
        constraint book_audit_pk primary key,
    created_by bigint    not null
        constraint book_audit_user_create_fk references "user",
    created_at timestamp not null default current_timestamp,
    updated_by bigint    not null
        constraint book_audit_user_update_fk references "user",
    updated_at timestamp not null default current_timestamp,
    book_id    bigint    not null
        constraint book_audit_book_fk references book,
    client_id  bigint    not null
        constraint book_audit_client_fk references client,
    book_state text      not null
);
