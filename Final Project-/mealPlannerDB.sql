DROP TABLE recipes_types CASCADE CONSTRAINTS;
DROP TABLE recipes CASCADE CONSTRAINTS;
DROP TABLE ingredients_types CASCADE CONSTRAINTS;
DROP TABLE ingredients CASCADE CONSTRAINTS;
DROP TABLE schedules CASCADE CONSTRAINTS;
DROP TABLE authorization_Levels CASCADE CONSTRAINTS;
DROP TABLE users_accounts CASCADE CONSTRAINTS;
DROP TABLE recipes_ingredients CASCADE CONSTRAINTS;
DROP TABLE schedules_recipes CASCADE CONSTRAINTS;

CREATE TABLE recipes_types (
	recipe_type_code NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	recipe_type VARCHAR(12) NOT NULL
);

CREATE TABLE recipes (
	recipe_code NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	name VARCHAR2 (20) NOT NULL,
	description VARCHAR(100) DEFAULT NULL,
	steps VARCHAR(100) NOT NULL,
	prep_time NUMBER NOT NULL,
	recipe_type NUMBER NOT NULL,
	FOREIGN KEY (recipe_type) REFERENCES recipes_types (recipe_type_code)
);

CREATE TABLE ingredients_types (
	ingredient_type_code NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	ingredient_type VARCHAR(12) NOT NULL
);

CREATE TABLE ingredients (
	ingredient_code NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	ingredient_name VARCHAR(12) NOT NULL,
	shelf_stable varchar2(1) NOT NULL,
	staple varchar2(1) NOT NULL,
	vegetarian varchar2(1) NOT NULL,
	vegan varchar2(1) NOT NULL
);

CREATE TABLE schedules (
	schedule_code VARCHAR(12) PRIMARY KEY
	--NOT SURE WHAT ELSE SHOULD BE APART OF THIS OBJECT
);

CREATE TABLE authorization_levels (
	level_code VARCHAR(12) PRIMARY KEY,
	descriptionS VARCHAR(30) DEFAULT NULL
);

CREATE TABLE users_accounts (
	user_code NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	first_name VARCHAR2 (20) DEFAULT NULL,
	last_name VARCHAR2 (25) NOT NULL,
	email VARCHAR2 (60) NOT NULL,
	phone VARCHAR2 (15) DEFAULT NULL,
	authorization_level VARCHAR2 (25) NOT NULL,
	FOREIGN KEY (authorization_level) REFERENCES authorization_levels (level_code)
);

CREATE TABLE recipes_ingredients (
	recipe_ingredient_code NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	recipe_code NUMBER NOT NULL,
	ingredient_code NUMBER NOT NULL,
	quantity NUMBER NOT NULL,
	qualifier VARCHAR(12) NOT NULL,
	FOREIGN KEY (recipe_code) REFERENCES recipes (recipe_code),
	FOREIGN KEY (ingredient_code) REFERENCES ingredients (ingredient_code)
);

CREATE TABLE schedules_recipes (
	schedule_recipe_code NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	schedule_code VARCHAR(12) NOT NULL,
	recipe_code NUMBER NOT NULL,
	FOREIGN KEY (schedule_code) REFERENCES schedules (schedule_code),
	FOREIGN KEY (recipe_code) REFERENCES recipes (recipe_code)
);

