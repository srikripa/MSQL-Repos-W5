CREATE DATABASE IF NOT EXISTS potluckDB;

USE potluckDB;

DROP TABLE IF EXISTS potluck_list;

CREATE TABLE potluck_list (
id int(11) NOT NULL auto_increment,
first_name varchar(25) NOT NULL,
last_name varchar(25) NOT NULL,
phone_number varchar(15) NOT NULL,
dish_type varchar(255) NOT NULL,
PRIMARY KEY(id)
);