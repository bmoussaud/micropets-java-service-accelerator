DROP TABLE IF EXISTS  PET_KIND;

CREATE TABLE PET_KIND (
    index SERIAL PRIMARY KEY,
    name VARCHAR(255),
    type VARCHAR(255),
    age integer,
    url VARCHAR(255),
    uri VARCHAR(255),
    hostname VARCHAR(255));
