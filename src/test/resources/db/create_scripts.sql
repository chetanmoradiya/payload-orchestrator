create schema if not exists cloudtechies;

drop table if exists payload;

CREATE TABLE if not exists payload(
    payload_id UUID not NULL,
    file_name VARCHAR (50) NOT NULL,
    payload_state VARCHAR(20) not null,
    rejection_reason VARCHAR(20),
    absolute_path VARCHAR (100) NOT NULL,
    create_ts TIMESTAMP NOT NULL,
    update_ts TIMESTAMP,
    last_modified_ts TIMESTAMP not null,
    instruction_count int4,
    resp_file_name VARCHAR(50),
    resp_file_path VARCHAR(100),
    constraint payload_pk primary key (payload_id,create_ts)
);

CREATE TABLE if not exists transaction_report(
 	txn_report_id UUID not null,
	payload_id UUID not null,
	create_ts timestamp not null,
	update_ts timestamp,
    txn_status varchar(20),
    rjct_reason jsonb,
  	trn_id varchar(100),
	cntrct_type varchar(100),
	action_type varchar(100),
	uti varchar(100),
	'level' varchar(100),
    rep_ctrpty_cd varchar(20),
 	rep_ctrpty_fin_sts varchar(100),
	rep_ctrpty_sec varchar(100),
    non_rep_ctrpty_cd varchar(20),
    non_rep_ctrpty_fin_sts varchar(100),
    non_rep_ctrpty_sec varchar(100),
    ctrpty_side varchar(100),
    event_date varchar(10),
    trading_venue varchar(100),
    mstr_agreement_typ varchar(100),
    value_dt varchar(10),
    gen_coll_ind varchar(100),
    typ_of_asset varchar(100),
    sec_identifier varchar(12),
    class_of_a_sec varchar(100),
    loan_base_prod varchar(100),
    loan_sub_prod varchar(100),
    loan_fur_sub_prod varchar(100),
    loan_lei_of_issuer varchar(100),
    loan_maturity_of_secu varchar(10),
    loan_juris_of_issuer varchar(100),
    constraint transaction_report_pk primary key (txn_report_id, create_ts)
);
