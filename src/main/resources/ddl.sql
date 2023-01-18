CREATE TABLE client
(
    id              UUID default gen_random_uuid(),
    name            varchar,
    phone_number    varchar not null unique,
    coach           int references coach (id),
    subscription_id integer references subscription (id),
    email           varchar not null unique,
    PRIMARY KEY (id)
);


CREATE TABLE subscription
(
    id          integer,
    title       varchar,
    price       decimal,
    description varchar,
    PRIMARY KEY (id)
);

CREATE TABLE coach
(
    id   serial,
    name varchar,

    PRIMARY KEY (id)
);

CREATE TABLE request_fit
(
    id           bigserial,
    title        varchar   default 'заявка на абонемента и тренера',
    req_date     timestamp default current_timestamp,
    sub_id       integer references subscription (id),
    coach_id     integer references coach (id),
    phone_number varchar,
    is_approved  boolean   default false,
    email        varchar references client (email),
    primary key (id)
);

CREATE TABLE manager
(
    id   serial,
    name varchar,
    PRIMARY KEY (id)
);

create table shedlock
(
    name       varchar(64)  not null,
    lock_until TIMESTAMP(3) not null,
    locked_at  TIMESTAMP(3) not null default current_timestamp(3),
    locked_by  varchar(255) not null,
    primary key (name)
);




