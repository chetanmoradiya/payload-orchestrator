create schema if not exists cloudtechies;

drop table if exists payload;

CREATE TABLE if not exists payload(
    payload_id UUID not NULL,
    file_name VARCHAR (50) NOT NULL,
    payload_state VARCHAR(20) not null,
    absolute_path VARCHAR (50) NOT NULL,
    create_ts TIMESTAMP NOT NULL,
    update_ts TIMESTAMP,
    last_modified_ts TIMESTAMP not null,
    instruction_count int4,
    resp_file_name VARCHAR(50),
    resp_file_path VARCHAR(50),
    constraint payload_pk primary key (payload_id,create_ts)
);
