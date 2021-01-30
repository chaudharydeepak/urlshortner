CREATE TABLE IF NOT EXISTS url_code_mapping ( id VARCHAR(36) PRIMARY KEY, code VARCHAR(250) NOT NULL UNIQUE,url VARCHAR(250) NOT NULL,  active varchar(1) default 'Y', requested_by VARCHAR(250) NOT NULL, created_date timestamp, updated_date timestamp, UNIQUE KEY code (code));

CREATE TABLE IF NOT EXISTS code_active ( id varchar(36), FOREIGN KEY (id) REFERENCES url_code_mapping, start_date timestamp, end_date timestamp);

/*CREATE SEQUENCE HIBERNATE_SEQUENCE START WITH 1 INCREMENT BY 1;*/