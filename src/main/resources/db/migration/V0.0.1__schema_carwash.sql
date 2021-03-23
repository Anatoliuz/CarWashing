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
    minutes int
);

create table carwash.washing_operation
(
    washing_id   bigint references carwash.washing (id),
    operation_id bigint references carwash.operation (id),
    primary key (washing_id, operation_id)
);


