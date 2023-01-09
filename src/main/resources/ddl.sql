CREATE TABLE client
(
    id              UUID default gen_random_uuid(),
    name            varchar,
    phone_number    varchar not null unique,
    coach           int references coach (id),
    subscription_id integer references subscription (id),
    PRIMARY KEY (id)
);

INSERT INTO client(name, phone_number, coach, subscription_id)
values ('Client1', '89215783626', 1, 1);

CREATE TABLE subscription
(
    id          integer,
    title       varchar,
    price       decimal,
    description varchar,
    PRIMARY KEY (id)
);

insert into subscription (id, title, price, description)
values (1, 'title1', 34845, 'Абонемент1');

CREATE TABLE coach
(
    id   serial,
    name varchar,
    PRIMARY KEY (id)
);

insert into coach (name)
values ('Coach1');

CREATE TABLE request_fit
(
    id       bigserial,
    title    varchar default 'заявка на абонемента и тренера',
    req_date timestamp,
    sub_id   integer references subscription (id),
    coach_id integer references coach (id),
    primary key (id)
);

CREATE TABLE manager
(
    id   serial,
    name varchar,
    PRIMARY KEY (id)
);


