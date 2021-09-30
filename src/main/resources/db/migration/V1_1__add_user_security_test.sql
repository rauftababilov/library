-- for test security purposes
insert into usr (id, username, password)
values (1, 'admin', 123);

insert into role(id, name)
values (1, 'ADMIN');

insert into user_role (role_id, user_id)
values (1, 1);