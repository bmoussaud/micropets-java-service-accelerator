DROP TABLE IF EXISTS  PetKindBean_TABLE;

CREATE TABLE PetKindBean_TABLE (
    index SERIAL PRIMARY KEY,
    name VARCHAR(255),
    type VARCHAR(255),
    age integer,
    url VARCHAR(255),
    uri VARCHAR(255),
    hostname VARCHAR(255));
