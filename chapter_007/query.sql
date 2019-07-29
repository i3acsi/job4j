CREATE TABLE cities (
name VARCHAR(100) NOT NULL  
);

INSERT INTO cities SELECT DISTINCT name FROM city;  

--SELECT name
--FROM city
--GROUP BY name;

TRUNCATE TABLE city RESTART IDENTITY;

INSERT INTO city (name)
SELECT name FROM cities;

DROP TABLE cities;