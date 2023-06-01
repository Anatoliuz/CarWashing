create schema carwash authorization test;

create table carwash.washing
(
    id               bigserial primary key,
    version          bigint    not null,
    create_date_time timestamp not null,
    delete_date_time timestamp,
    start_time       timestamp not null,
    end_time         timestamp not null
);

create table carwash.operation
(
    id      bigserial primary key,
    name    varchar(64) not null unique,
    label   varchar(64) not null unique,
    minutes int
);

create table carwash.operation_price
(
    id               bigserial primary key,
    operation_id     bigint references carwash.operation (id),
    price            int       not null,
    create_date_time timestamp not null,
    is_archived      bool
);

create table carwash.washing_operation_price
(
    washing_id         bigint references carwash.washing (id),
    operation_price_id bigint references carwash.operation_price (id),
    primary key (washing_id, operation_price_id)
);


insert into carwash.operation (name, label, minutes)
values ('hand_wash', 'Ручная мойка', 10),
       ('wireless_wash', 'Бесконтактная мойка', 15),
       ('washing_rugs', 'Мойка ковриков', 1);

insert into carwash.operation_price (operation_id, price, create_date_time, is_archived)
values (1, 300, now(), false),
       (2, 400, now(), false),
       (3, 100, now(), false)
