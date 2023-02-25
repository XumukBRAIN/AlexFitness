CREATE SEQUENCE orders_number_seq start with 1;

create sequence hibernate_sequence;

create table if not exists accountant
(
    id
    integer
    not
    null
    constraint
    accountant_pkey
    primary
    key,
    balance
    numeric,
    name
    varchar
);

create table if not exists client
(
    id uuid default gen_random_uuid
(
) not null
    constraint client_pkey
    primary key,
    name varchar,
    phone_number varchar not null
    constraint client_phone_number_key
    unique,
    coach integer
    constraint client_coach_fkey
    references coach,
    subscription_id integer
    constraint client_subscription_id_fkey
    references subscription,
    balance numeric,
    email varchar not null
    constraint client_email_key
    unique,
    password varchar,
    role varchar default 'ROLE_USER':: character varying,
    double_check boolean default false,
    account_is_locked boolean default false,
    end_ban_date date
    );

create table if not exists coach
(
    id
    serial
    not
    null
    constraint
    coach_pkey
    primary
    key,
    name
    varchar,
    password
    varchar
    default
    12345678,
    email
    varchar
    not
    null,
    role
    varchar
    default
    'ROLE_COACH'
    :
    :
    character
    varying,
    double_check
    boolean
    default
    false,
    phone_number
    varchar
    not
    null
);

create table if not exists item
(
    id
    serial
    not
    null
    constraint
    item_pkey
    primary
    key,
    title
    varchar
    not
    null,
    price
    numeric
    not
    null
);

create table if not exists manager
(
    id
    serial
    not
    null
    constraint
    manager_pkey
    primary
    key,
    name
    varchar,
    email
    varchar,
    password
    varchar
    default
    12345678,
    role
    varchar
    default
    'ROLE_ADMIN'
    :
    :
    character
    varying
    not
    null,
    double_check
    boolean
    default
    false,
    phone_number
    varchar
    not
    null
);

create table if not exists orders
(
    id
    serial
    not
    null
    constraint
    orders_pkey
    primary
    key,
    client_id
    uuid
    constraint
    orders_client_id_fkey
    references
    client,
    number
    varchar,
    title
    varchar,
    sum
    numeric
    default
    0.0,
    req_date
    timestamp
    default
    CURRENT_TIMESTAMP,
    phone_number
    varchar
);

create table if not exists orders_item
(
    orders_id
    integer
    not
    null
    constraint
    orders_item_orders_id_fkey
    references
    orders,
    item_id
    integer
    not
    null
    constraint
    orders_item_item_id_fkey
    references
    item,
    id
    serial
    not
    null
    constraint
    orders_item_pk
    primary
    key
);

create table if not exists request_fit
(
    id
    bigserial
    not
    null
    constraint
    request_fit_pkey
    primary
    key,
    title
    varchar
    default
    'заявка на абонемента и тренера'
    :
    :
    character
    varying,
    req_date
    timestamp
    default
    CURRENT_TIMESTAMP,
    sub_id
    integer
    constraint
    request_fit_sub_id_fkey
    references
    subscription,
    coach_id
    integer
    constraint
    request_fit_coach_id_fkey
    references
    coach,
    phone_number
    varchar,
    is_approved
    boolean
    default
    false,
    email
    varchar
);

create table if not exists subscription
(
    id
    integer
    not
    null
    constraint
    subscription_pkey
    primary
    key,
    title
    varchar,
    price
    numeric,
    description
    varchar
);