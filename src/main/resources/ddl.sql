CREATE TABLE visitor(
    id serial,
    name varchar,
    phone_number varchar,
    coach_id int references coach(id),
    PRIMARY KEY (id)
);

insert into visitor(name, phone_number, coach)
VALUES ('Алекс', '+79213633374', 1);

insert into coach(name) values ('Василий');


CREATE TABLE coach(
    id serial,
    name varchar,
    PRIMARY KEY (id)
);

CREATE TABLE manager(
    id serial,
    name varchar,
    PRIMARY KEY (id)
);