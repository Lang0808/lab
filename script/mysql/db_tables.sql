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
    gmt_modified DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);