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

INSERT INTO potluck_list(first_name, last_name, phone_number, dish_type) VALUES 
("Tom", "Smith", 1111111111,"Greens Salad"),
("Tim", "Chamath", 2222222222, "Vegetable Fried Rice"),
("Sally", "Henson", 3333333333, "Soda"),
("Chris", "Jenkins", 4444444444, "Peach Pie"),
("Kevin", "Mcadoo", 555555555, "Napkins and cutlery");
