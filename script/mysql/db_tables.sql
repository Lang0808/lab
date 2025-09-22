create table transactions (
    transaction_id varchar(50) not null,
    status varchar(20) not null,
    sender varchar(50),
    receiver varchar(50),
    product_id varchar(50),
    price bigint,
    quantity int,
    total bigint,
    gmt_create DATETIME DEFAULT CURRENT_TIMESTAMP,
    gmt_modified DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key (transaction_id)
);

create table products (
    product_id varchar(50) not null,
    status varchar(20) not null,
    merchant varchar(50) not null,
    price bigint not null,
    creator varchar(50) not null,
    name varchar(200) not null,
    description text,
    gmt_create DATETIME DEFAULT CURRENT_TIMESTAMP,
    gmt_modified DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key (product_id)
);

create table merchants (
    merchant_id varchar(50) not null,
    status varchar(20) not null,
    name varchar(200) not null,
    description text,
    gmt_create DATETIME DEFAULT CURRENT_TIMESTAMP,
    gmt_modified DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key (merchant_id)
);

create table users (
    user_id varchar(50) not null,
    status varchar(20) not null,
    name varchar(200) not null,
    gmt_create DATETIME DEFAULT CURRENT_TIMESTAMP,
    gmt_modified DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    balance bigint not null default 0,
    primary key (user_id)
);