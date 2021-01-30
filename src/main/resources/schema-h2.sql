DROP TABLE IF EXISTS url_code_mapping;

CREATE TABLE url_code_mapping (
  id INT PRIMARY KEY,
  url VARCHAR(250) NOT NULL,
  code VARCHAR(250) NOT NULL,
  requested_by VARCHAR(250) NOT NULL,
  created_date timestamp,
  updated_date timestamp
);

CREATE SEQUENCE HIBERNATE_SEQUENCE START WITH 1 INCREMENT BY 1;