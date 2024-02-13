BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, deck, card;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE SEQUENCE seq_deck_id
  INCREMENT BY 1
  START WITH 1001
  NO MAXVALUE;

CREATE TABLE deck (
   deck_id int NOT NULL DEFAULT nextval('seq_deck_id'),
   username varchar(50) NOT NULL,
   deck_title varchar(50) NOT NULL,
   deck_tags varchar(50) NOT NULL,
   is_public boolean,
   CONSTRAINT PK_deck PRIMARY KEY (deck_id)
);

CREATE SEQUENCE seq_card_id
  INCREMENT BY 1
  START WITH 3001
  NO MAXVALUE;

CREATE TABLE card (
	card_id int NOT NULL DEFAULT nextval('seq_card_id'),
	username varchar(50) NOT NULL,
	front_text varchar(50) NOT NULL,
	back_text varchar(50) NOT NULL,
	card_tags varchar(50) NOT NULL,
	is_public boolean,
	CONSTRAINT PK_card PRIMARY KEY (card_id)
);

COMMIT TRANSACTION;
