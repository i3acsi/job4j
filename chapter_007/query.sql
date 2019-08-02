CREATE TABLE cities (
name VARCHAR(100) NOT NULL  
);

INSERT INTO cities SELECT DISTINCT name FROM city;  

--SELECT name
--FROM city
--GROUP BY name;

DROP TABLE city;

CREATE TABLE city(
ID SERIAL PRIMARY KEY,
name VARCHAR(100) UNIQUE NOT NULL 
);

INSERT INTO city (name)
SELECT name FROM cities;

DROP TABLE cities;

SELECT * FROM city;
-------------------
DROP TABLE city;
CREATE TABLE city (
ID SERIAL PRIMARY KEY,
name VARCHAR(100) NOT NULL
);
INSERT INTO city (name) VALUES ('Kazan');
INSERT INTO city (name) VALUES ('Moscow');
INSERT INTO city (name) VALUES ('Novosibirsk');
INSERT INTO city (name) VALUES ('Kazan');
INSERT INTO city (name) VALUES ('Novosibirsk');
INSERT INTO city (name) VALUES ('Saint_Petersburg');
INSERT INTO city (name) VALUES ('Kazan');
INSERT INTO city (name) VALUES ('Moscow');
INSERT INTO city (name) VALUES ('Saint_Petersburg');
INSERT INTO city (name) VALUES ('Kazan');
INSERT INTO city (name) VALUES ('Minsk');

CREATE VIEW ids AS
WITH dubl(name) AS (SELECT name from city GROUP BY name HAVING COUNT(name)>1),
one(id) AS (SELECT MAX(id) FROM city GROUP BY name HAVING COUNT(name)>1)
SELECT 
c.id
FROM city c
INNER JOIN dubl ON dubl.name=c.name
LEFT JOIN one ON one.id=c.id
WHERE one.id IS NULL;

DELETE FROM city WHERE id IN (SELECT id FROM ids);