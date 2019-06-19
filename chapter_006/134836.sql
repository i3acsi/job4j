CREATE TABLE if NOT EXISTS type (
	id SERIAL PRIMARY KEY NOT NULL,
	name VARCHAR (100) NOT NULL
);

CREATE TABLE if NOT EXISTS product (
	id SERIAL PRIMARY KEY NOT NULL,
	name VARCHAR (100) NOT NULL,
	type_id INT REFERENCES type(id) NOT NULL,
	expired_date TIMESTAMP NOT NULL,
	price DECIMAL(6,2) NOT NULL
);

INSERT INTO type(name) VALUES ('drink');
INSERT INTO type(name) VALUES ('bread');
INSERT INTO type(name) VALUES ('meat');
INSERT INTO type(name) VALUES ('sweet');
INSERT INTO type(name) VALUES ('cheese');
INSERT INTO type(name) VALUES ('milk');

INSERT INTO product(name, type_id, expired_date, price) VALUES (
	'cola', 1, '2019-09-28 23:59:59', 096.00
);
INSERT INTO product(name, type_id, expired_date, price) VALUES (
	'tea', 1, '2019-07-14 23:59:59', 030.52
);

INSERT INTO product(name, type_id, expired_date, price) VALUES (
	'borodinskiy', 2, '2019-06-20 23:59:59', 030.00
);

INSERT INTO product(name, type_id, expired_date, price) VALUES (
	'pork', 3, '2019-07-1 23:59:59', 350.00
);

INSERT INTO product(name, type_id, expired_date, price) VALUES (
	'candy', 4, '2019-12-20 23:59:59', 052.43
);
INSERT INTO product(name, type_id, expired_date, price) VALUES (
	'chocolate ice cream', 4, '2019-09-12 23:59:59', 060.10
);
INSERT INTO product(name, type_id, expired_date, price) VALUES (
	'raspberry ice cream', 4, '2019-08-03 23:59:59', 044.43
);

INSERT INTO product(name, type_id, expired_date, price) VALUES (
	'poshehonskiy', 5, '2019-07-21 23:59:59', 106.42
);
INSERT INTO product(name, type_id, expired_date, price) VALUES (
	'parmigiano', 5, '2019-08-03 23:59:59', 212.34
);
INSERT INTO product(name, type_id, expired_date, price) VALUES (
	'cheddar', 5, '2019-08-01 23:59:59', 190.12
);
INSERT INTO product(name, type_id, expired_date, price) VALUES (
	'roquefort', 5, '2019-07-28 23:59:59', 250.69
);

INSERT INTO product(name, type_id, expired_date, price) VALUES (
	'just a milk', 6, '2019-06-25 23:59:59', 052.20
);

-- Написать запрос получение всех продуктов с типом "СЫР"
SELECT *
FROM product p
LEFT OUTER JOIN type t
ON p.type_id=t.id
WHERE t.name = 'cheese';

-- Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
SELECT * FROM product
WHERE name LIKE '%ice cream%';

-- Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
SELECT * FROM product p
WHERE p.expired_date BETWEEN date_trunc('month', CURRENT_DATE + interval '1' month)
and date_trunc('month', CURRENT_DATE + interval '2' month);

-- Написать запрос, который выводит самый дорогой продукт.
SELECT * FROM product p
WHERE p.price = (SELECT MAX(p.price) FROM product p);

-- Написать запрос, который выводит количество всех продуктов определенного типа.
SELECT COUNT(p.id)
FROM product p
LEFT OUTER JOIN type t
ON p.type_id=t.id
WHERE t.name = 'cheese';

--Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT *
FROM product p
LEFT OUTER JOIN type t
ON p.type_id=t.id
WHERE t.name = 'cheese'
OR t.name='milk';

-- Написать запрос, который выводит тип продуктов, которых осталось меньше 3 штук.
SELECT t.name, COUNT(p.name)
FROM product p
INNER JOIN
type t
ON p.type_id = t.id
GROUP BY t.name
HAVING COUNT(p.name) < 3;

-- Вывести все продукты и их тип.
SELECT p.name, t.name
FROM product p
INNER JOIN
type t
ON p.type_id = t.id;