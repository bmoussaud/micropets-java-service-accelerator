DROP TABLE IF EXISTS  PET_KIND_BEAN;

CREATE TABLE PET_KIND_BEAN (
    index SERIAL PRIMARY KEY,
    name VARCHAR(255),
    type VARCHAR(255),
    age integer,
    url VARCHAR(255),
    uri VARCHAR(255),
    hostname VARCHAR(255));
