-- for test security purposes
insert into usr (id, username, password)
values (1, 'admin', '$2a$10$1NCuFOi0EnOuk.63SjVya.XCgwGNlhJ87WRJ5seA4hy12bcNE7rG6');

insert into role(id, name)
values (1, 'ADMIN');

insert into user_role (role_id, user_id)
values (1, 1);