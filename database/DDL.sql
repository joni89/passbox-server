DROP TABLE IF EXISTS credentials;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
	id SERIAL PRIMARY KEY,
	username VARCHAR(100) NOT NULL,
	password VARCHAR(100) NOT NULL
);

CREATE TABLE credentials (
	id SERIAL PRIMARY KEY,
	description VARCHAR(100),
	username VARCHAR(100) NOT NULL,
	password VARCHAR(100) NOT NULL,
	url VARCHAR(40),
	comments VARCHAR(300),
	user_id BIGINT NOT NULL,
	FOREIGN KEY (user_id) REFERENCES users(id)
);
