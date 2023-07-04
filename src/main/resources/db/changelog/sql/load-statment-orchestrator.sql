COPY instrument_data(typ_of_asset, sec_identifier, class_of_a_sec, loan_base_prod, loan_sub_prod, loan_fur_sub_prod, loan_lei_of_issuer, loan_maturity_of_secu, loan_juris_of_issuer)
FROM '/hackathon/temp/mnt/instruments/instrumentData.csv'
DELIMITER ','
CSV HEADER;