INSERT INTO users(first_name, last_name, email, password, status)
values ('Vlad', 'Kharchenko', 'vlad.kharchenko55@gmail.com',
        '$2a$04$Hd84RUNYKwvWV278001E0.WetNXlmlg8HbD/sksXBAP2yAsxKotZG', 'OLD_USER');
INSERT INTO users(first_name, last_name, email, password, status)
values ('Max', 'Petrenko', 'max@gmail.com', '$2a$04$/Gy7u/Win7xJOZ3q/bDa.OsraiftDVZvzIM31RI6ZBdYrzwRbDFDa', 'NEWCOMER');
INSERT INTO users(first_name, last_name, email, password, status)
values ('Dima', 'Bilan', 'dima@gmail.com', '$2a$04$nmFSaiV19r.stDs0Upw9LeU7EQRxZFWlOTfkcy44JOa.vEboLT4MS', 'OLD_USER');
INSERT INTO users(first_name, last_name, email, password, status)
values ('Peter', 'Brown', 'peter@gmail.com', '$2a$04$EjL85FP66CB9HQ0gPF5BguWsrqx/CZHcSenHvdoFJD0KzHZ.Ot8Qa',
        'NEWCOMER');
INSERT INTO users(first_name, last_name, email, password, status)
values ('Harry', 'Wilson', 'harry@gmail.com', '$2a$04$LoPWjvmJ5.i4XFpAwyz/oehVYU6ux9bxdppfkycpIy6R2XYlgkKgi',
        'NEWCOMER');
INSERT INTO users(first_name, last_name, email, password, status)
values ('Jim', 'Miller', 'miller@gmail.com', '$2a$04$lnocSL51a5nla2dlAvc7gOT5tbxT4jfi.FzHs2ZHn/li6tvDqYBa.',
        'OLD_USER');
INSERT INTO users(first_name, last_name, email, password, status)
values ('Ted', 'Mosby', 'ted@gmail.com', '$2a$04$/4Tp6pos4jGjAc/wm6Jnd.DEzze/agxDynUX9rg0PvDs0SB0VTtgq', 'OLD_USER');

INSERT INTO tasks(title, description, status, user_id)
VALUES ('Main task', 'Some description about main task', 'VIEW', 1);
INSERT INTO tasks(title, description, status, user_id)
VALUES ('Easy task', 'Some description about easy task', 'IN_PROGRESS', 2);
INSERT INTO tasks(title, description, status, user_id)
VALUES ('Big task', 'Some description about big task', 'DONE', 3);
INSERT INTO tasks(title, description, status, user_id)
VALUES ('Small task', 'Some description about small task', 'IN_PROGRESS', 4);
INSERT INTO tasks(title, description, status, user_id)
VALUES ('Interesting task', 'Some description about interesting task', 'DONE', 5);
INSERT INTO tasks(title, description, status, user_id)
VALUES ('Boring task', 'Some description about boring task', 'IN_PROGRESS', 6);
INSERT INTO tasks(title, description, status, user_id)
VALUES ('Enormous task', 'Some description about enormous task', 'VIEW', 3);
INSERT INTO tasks(title, description, status, user_id)
VALUES ('Nice task', 'Some description about nice task', 'DONE', 1);