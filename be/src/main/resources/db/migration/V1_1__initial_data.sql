insert into "user" (id, username, password)
values ((select nextval('seq_user')), 'admin', '$2a$10$oe3/AzO1cHOJY7RoEm7yfuPzQL.iKAH6wh8xqgbqBdMHXwI8rEaTu');

insert into role(id, name)
values (1, 'ADMIN');

insert into user_role (role_id, user_id)
values (1, (select currval('seq_user')));

insert into role(id, name)
values (2, 'ROOT');

insert into user_role (role_id, user_id)
values (2, (select currval('seq_user')));
