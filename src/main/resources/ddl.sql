CREATE TABLE client
(
    id              UUID default gen_random_uuid(),
    name            varchar,
    phone_number    varchar not null unique,
    coach           int references coach (id),
    subscription_id integer references subscription (id),
    PRIMARY KEY (id)
);

INSERT INTO client(name, phone_number)
values ('Client111', '89370019090');

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
    id           bigserial,
    title        varchar   default 'заявка на абонемента и тренера',
    req_date     timestamp default current_timestamp,
    sub_id       integer references subscription (id),
    coach_id     integer references coach (id),
    phone_number varchar,
    is_approved  boolean   default false,
    primary key (id)
);



insert into request_fit(title, sub_id, coach_id, phone_number)
values ('title111', 1, 1, '89370019090');

insert into request_fit(title, sub_id, coach_id, phone_number)
values ('абонемент129', 1, 1, 89205783624);

drop table request_fit;

CREATE TABLE manager
(
    id   serial,
    name varchar,
    PRIMARY KEY (id)
);

drop table manager;

CREATE SEQUENCE hibernate_sequence START 1;




