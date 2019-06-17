CREATE DATABASE db_134833;

-- user - role = many to one
-- т.е. одна роль может быть у разных пользователей
-- а у одного пользователя - одна роль
CREATE TABLE if NOT EXISTS role (
	id SERIAL PRIMARY KEY NOT NULL,
	name VARCHAR(100) NOT NULL
);

-- создадим таблицу пользователей
CREATE TABLE if NOT EXISTS users (
	id SERIAL PRIMARY KEY NOT NULL,
	login VARCHAR (100) NOT NULL,
	date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	password VARCHAR(100) NOT NULL,
	role_id INT REFERENCES role(id) NOT NULL
);

-- role - rules = many to many
-- создадим таблицу прав
CREATE TABLE if NOT EXISTS rule (
	id SERIAL PRIMARY KEY NOT NULL,
	rule VARCHAR(100) NOT NULL
);

-- Теперь нужно связать таблицы, для этого добавить
-- вспомагательную таблицу role_rules
CREATE TABLE if NOT EXISTS role_rules (
	id SERIAL PRIMARY KEY NOT NULL,
	rule_id INT REFERENCES rule(id) NOT NULL,
	role_id INT REFERENCES role(id) NOT NULL
);

-- Создадим таблицу состояний для заявок
CREATE TABLE if NOT EXISTS state (
	id SERIAL PRIMARY KEY NOT NULL,
	description VARCHAR(300) NOT NULL
);

-- Создадим таблицу категорий для заявок
CREATE TABLE if NOT EXISTS category (
	id SERIAL PRIMARY KEY NOT NULL,
	name VARCHAR(100) NOT NULL
);

CREATE TABLE if NOT EXISTS item (
	id SERIAL PRIMARY KEY NOT NULL,
	users_id int references users(id) NOT NULL,
	description VARCHAR(300) NOT NULL,
	date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	state_id INT REFERENCES state(id) NOT NULL,
	price DECIMAL(5,2) NOT NULL,
	category_id INT REFERENCES category(id) NOT NULL
);

CREATE TABLE if NOT EXISTS comments (
	id SERIAL PRIMARY KEY NOT NULL,
	comment VARCHAR(300) NOT NULL,
	item_id INT REFERENCES item(id) NOT NULL
);

CREATE TABLE if NOT EXISTS attachs (
	id SERIAL PRIMARY KEY NOT NULL,
	attach VARCHAR(300) NOT NULL,
	item_id INT REFERENCES item(id) NOT NULL
);

INSERT INTO role(name) VALUES ('admin');
INSERT INTO role(name) VALUES ('master');

INSERT INTO rule(rule) VALUES ('create');
INSERT INTO rule(rule) VALUES ('delete');
INSERT INTO rule(rule) VALUES ('comment');
INSERT INTO rule(rule) VALUES ('change state');

INSERT INTO role_rules (rule_id, role_id) VALUES (
	4,
	2
);

INSERT INTO users(login, password, role_id) VALUES ('Master1', 'password',
	2
);

INSERT INTO state(description) VALUES ('in process');

INSERT INTO category(name) VALUES ('simple');

INSERT INTO item(users_id, description, state_id, price, category_id) VALUES (
	1,
	'new description',
	1,
	11.11,
	1
);

INSERT INTO comments(comment, item_id) VALUES (
	'new comment',
	1
);

INSERT INTO attachs(attach, item_id) VALUES (
	'new File',
	1
);
