-- ==============================================================
-- Periodicals DB creation script for MySQL
-- ==============================================================

use period;

drop table if exists subscriptions;
drop table if exists editions;
drop table if exists statused;
drop table if exists categories;
drop table if exists users;
drop table if exists roles;
drop table if exists active;

CREATE TABLE roles(

-- id has the INTEGER type (other name is INT), it is the primary key
	id INTEGER NOT NULL PRIMARY KEY,
-- name has the VARCHAR type - a string with a variable length
-- names values should not be repeated (UNIQUE)
	rols VARCHAR(10) NOT NULL UNIQUE
);

-- this two commands insert data into roles table
-- --------------------------------------------------------------
-- ATTENTION!!!
-- we use ENUM as the Role entity, so the numeration must started
-- from 0 with the step equaled to 1
-- --------------------------------------------------------------
INSERT INTO roles VALUES (0, 'admin');
INSERT INTO roles VALUES (1, 'user');

-- --------------------------------------------------------------
-- ACTIVE
-- --------------------------------------------------------------

CREATE TABLE active(
	id INTEGER NOT NULL PRIMARY KEY,
    active VARCHAR(10) NOT NULL UNIQUE
);
INSERT INTO active VALUES (0, 'active');
INSERT INTO active VALUES (1, 'notActive');

-- --------------------------------------------------------------
-- USERS
-- --------------------------------------------------------------

CREATE TABLE users(
	id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,

-- 'UNIQUE' means logins values should not be repeated in login column of table
	login VARCHAR(30) NOT NULL UNIQUE,
	password VARCHAR(30) NOT NULL,
	firstName VARCHAR(30),
	lastName VARCHAR(30),
	localeName VARCHAR(10),

-- this declaration contains the foreign key constraint
-- role_id in users table is associated with id in roles table
-- role_id of user = id of role
	roleId INTEGER NOT NULL,
    money INTEGER NOT NULL,
    activeId INTEGER NOT NULL,
-- removing a row with some ID from roles table implies removing
-- all rows from users table for which ROLES_ID=ID
-- default value is ON DELETE RESTRICT, it means you cannot remove
-- row with some ID from the roles table if there are rows in
-- users table with ROLES_ID=ID
    FOREIGN KEY (roleId) REFERENCES roles (id) ON DELETE CASCADE,
    FOREIGN KEY (activeId) REFERENCES active (id) ON DELETE CASCADE
);
-- id = 1
INSERT INTO users VALUES(DEFAULT, 'admin', 'a', 'Ivan', 'Ivanov','en', 0, 0,0);
-- id = 2
INSERT INTO users VALUES(DEFAULT, 'client', 'c', 'Vaca', 'Petrov','ru', 1, 2000, 0);

-- --------------------------------------------------------------
-- CATEGORIES
-- Categories of editions
-- --------------------------------------------------------------
CREATE TABLE categories (
	id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    category  VARCHAR(50) NOT NULL UNIQUE,
    categoryRu  VARCHAR(50) NOT NULL
);
--id = 1
INSERT INTO categories VALUES (DEFAULT, 'Auto', 'Авто');
--id = 2
INSERT INTO categories VALUES (DEFAULT, 'Sport', 'Спорт');

-- --------------------------------------------------------------
-- EDITIONS
-- List of editions
-- --------------------------------------------------------------
CREATE TABLE editions(
	id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    edName VARCHAR(50),
    priceMonth INTEGER NOT NULL,
    categoryId INTEGER NOT NULL,
    frequency INTEGER NOT NULL,
    FOREIGN KEY (categoryId) REFERENCES categories (id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO editions VALUES (DEFAULT, 'EcoDrive', 39, 1, 4);
INSERT INTO editions VALUES (DEFAULT, 'EcoDrive', 100, 2, 1);
INSERT INTO editions VALUES (DEFAULT, '4x4Club', 249, 1, 1);
INSERT INTO editions VALUES (DEFAULT, 'Football', 70, 2, 8);
INSERT INTO editions VALUES (DEFAULT, 'Fan', 83, 2, 1);

-- --------------------------------------------------------------
-- STATUSES
-- Subscription payment status
-- --------------------------------------------------------------
CREATE TABLE statused(
	id INTEGER NOT NULL PRIMARY KEY,
    st VARCHAR(10) NOT NULL UNIQUE
);
--status not paid
INSERT INTO statused VALUES (0, 'notPaid');
--status paid
INSERT INTO statused VALUES (1, 'paid');

-- --------------------------------------------------------------
-- SUBSCRIPTIONS
-- Subscriptions of users
-- --------------------------------------------------------------
CREATE TABLE subscriptions(
	id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nameEdition VARCHAR(50),
    priceEd INTEGER NOT NULL,
    startMonth INTEGER NOT NULL,
    endMonth INTEGER NOT NULL,
    sum INTEGER NOT NULL,
    yearEd INTEGER NOT NULL,
    userId INTEGER NOT NULL,
    statusId INTEGER NOT NULL,
    dates DATE NOT NULL,
    editionId INTEGER NOT NULL,
    FOREIGN KEY (editionId) REFERENCES editions (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (userId) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (statusId) REFERENCES statused (id) ON DELETE CASCADE ON UPDATE CASCADE
);
-- id=1 (test subscription)
INSERT INTO subscriptions VALUES (DEFAULT, 'EcoDrive', 39, 2, 10, 312, 2019, 2, 1, '2019-02-27',1);

-- --------------------------------------------------------------
-- test database:
-- --------------------------------------------------------------
select * from roles;
select * from active;
select * from users;
select * from statused;
select * from subscriptions;
select * from categories;
select * from editions;