CREATE SCHEMA IF NOT EXISTS command;

ALTER DATABASE
    tasktracker SET search_path = command, public;

CREATE TABLE command.roles
(
    "id"   bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "title" text NOT NULL UNIQUE
);

CREATE TABLE command.groups
(
    "id"   bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "title" text NOT NULL UNIQUE
);

CREATE TABLE command.users
(
    "id"       bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "chat_id"    varchar(15) NOT NULL UNIQUE,
    "full_name"    text NOT NULL,
    "group_id" bigint,
    "role_id" bigint,
    CONSTRAINT fk_roles
        FOREIGN KEY(role_id)
            REFERENCES command.roles(id),
    CONSTRAINT fk_groups
        FOREIGN KEY(group_id)
            REFERENCES command.groups(id)
);

INSERT INTO command.roles (title)
VALUES ('USER'),
       ('TEAMLEAD'),
       ('TEACHER'),
       ('ADMIN');

INSERT INTO command.groups (title)
VALUES ('BLUE');

INSERT INTO command.users (chat_id, full_name, group_id, role_id)
VALUES ('497542778', 'Ilya', 1, 1),
    ('296732256', 'Valeria', 1, 1),
    ('880825037', 'Ramzan', 1, 1),
    ('261927286', 'Maria', 1, 1),
    ('355086790', 'Margarita', 1, 1),
    ('790376269', 'Yaroslav', 1, 1),
    ('356840503', 'Gleb', 1, 1),
    ('670159425', 'Konstantin Kozhevnikov', null, 3);