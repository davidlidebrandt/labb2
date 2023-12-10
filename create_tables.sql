CREATE DATABASE IF NOT EXISTS placedata;
USE placedata;

CREATE TABLE IF NOT EXISTS Category(
id BINARY(255),
name_ VARCHAR(255) NOT NULL UNIQUE,
symbol VARCHAR(255) NOT NULL,
description_ TEXT NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS Place(
id BINARY(255),
name_ VARCHAR(255),
category BINARY,
user_id VARCHAR(255),
visibility VARCHAR(255),
last_modified DATETIME,
description_ TEXT NOT NULL,
coordinate GEOMETRY NOT NULL SRID 4326,
created DATETIME NOT NULL,
PRIMARY KEY(name_),
FOREIGN KEY(category) REFERENCES category(id)
);