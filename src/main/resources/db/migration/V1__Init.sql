CREATE SCHEMA IF NOT EXISTS command;

ALTER DATABASE
    tasktracker SET search_path = command, public;

CREATE TABLE "users"
(
    "id"       bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "chat_id"    varchar(15) NOT NULL UNIQUE,
    "full_name"    text NOT NULL,
    "group_id" bigint,
    "role_id" bigint
);

CREATE TABLE "roles"
(
    "id"   bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "title" text NOT NULL UNIQUE
);

CREATE TABLE "groups"
(
    "id"   bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "title" text NOT NULL UNIQUE
);

ALTER TABLE command.users ADD CONSTRAINT fk_users_groups_group_id FOREIGN KEY (group_id)
    REFERENCES groups;
ALTER TABLE command.users ADD CONSTRAINT fk_users_roles_role_id FOREIGN KEY (role_id)
    REFERENCES roles;


INSERT INTO roles (title)
VALUES ('USER'),
       ('TEAMLEAD'),
       ('TEACHER'),
       ('ADMIN');