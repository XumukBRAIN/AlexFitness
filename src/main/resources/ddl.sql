CREATE TABLE visitor(
    id serial,
    name varchar,
    phone_number varchar,
    coach int references coach(id),
    subscription_id integer references subscription(id),
    PRIMARY KEY (id)
);

CREATE TABLE subscription(
    id integer,
    title varchar,
    price decimal,
    description varchar,
    PRIMARY KEY (id)
);

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