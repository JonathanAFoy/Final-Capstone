BEGIN TRANSACTION;
INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('christopher','$2a$10$dgGIiaOtk5E3CUrtb96X9.cHamto0BCthX6ahaJsVzAh.rWLpyHaG','ROLE_USER');

INSERT INTO card (username,front_text,back_text,card_tags,is_public) VALUES ('christopher','Capital Of France','Paris','Countries, Capitals',false);
INSERT INTO card (username,front_text,back_text,card_tags,is_public) VALUES ('christopher','Capital Of Germany','Berlin','Countries, Capitals',false);
INSERT INTO card (username,front_text,back_text,card_tags,is_public) VALUES ('christopher','Capital Of Turkey','Ankara','Countries, Capitals',false);
INSERT INTO card (username,front_text,back_text,card_tags,is_public) VALUES ('christopher','Capital Of Serbia','Belgrade','Countries, Capitals',false);
INSERT INTO card (username,front_text,back_text,card_tags,is_public) VALUES ('christopher','Capital Of Philippines','Manila','Countries, Capitals',false);
INSERT INTO card (username,front_text,back_text,card_tags,is_public) VALUES ('christopher','Capital Of New Zealand','Wellington','Countries, Capitals',false);
INSERT INTO card (username,front_text,back_text,card_tags,is_public) VALUES ('christopher','Capital Of Nepal','Kathmandu','Countries, Capitals',false);
INSERT INTO card (username,front_text,back_text,card_tags,is_public) VALUES ('christopher','Capital Of Italy','Rome','Countries, Capitals',false);
INSERT INTO card (username,front_text,back_text,card_tags,is_public) VALUES ('christopher','Capital Of Indonesia','Jakarta','Countries, Capitals',false);
INSERT INTO card (username,front_text,back_text,card_tags,is_public) VALUES ('christopher','Capital Of Greece','Athens','Countries, Capitals',false);
INSERT INTO card (username,front_text,back_text,card_tags,is_public) VALUES ('christopher','Capital Of Venezuela','Caracas','Countries, Capitals',false);
INSERT INTO card (username,front_text,back_text,card_tags,is_public) VALUES ('christopher','Capital Of Spain','Madrid','Countries, Capitals',false);
INSERT INTO card (username,front_text,back_text,card_tags,is_public) VALUES ('christopher','Capital Of Taiwan','Taipei','Countries, Capitals',false);
INSERT INTO card (username,front_text,back_text,card_tags,is_public) VALUES ('christopher','Capital Of Ukraine','Kiev','Countries, Capitals',false);
INSERT INTO card (username,front_text,back_text,card_tags,is_public) VALUES ('christopher','Capital Of Montenegro','Podgorica','Countries, Capitals',false);

INSERT INTO deck (username,deck_title,deck_tags,is_public) VALUES ('christopher','Countries & Capitals','Geography',false);

INSERT INTO deck_card (deck_id,card_id) VALUES (1001,3001);
INSERT INTO deck_card (deck_id,card_id) VALUES (1001,3002);
INSERT INTO deck_card (deck_id,card_id) VALUES (1001,3003);
INSERT INTO deck_card (deck_id,card_id) VALUES (1001,3004);
INSERT INTO deck_card (deck_id,card_id) VALUES (1001,3005);
INSERT INTO deck_card (deck_id,card_id) VALUES (1001,3006);
INSERT INTO deck_card (deck_id,card_id) VALUES (1001,3007);
INSERT INTO deck_card (deck_id,card_id) VALUES (1001,3008);
INSERT INTO deck_card (deck_id,card_id) VALUES (1001,3009);
INSERT INTO deck_card (deck_id,card_id) VALUES (1001,3010);
INSERT INTO deck_card (deck_id,card_id) VALUES (1001,3011);
INSERT INTO deck_card (deck_id,card_id) VALUES (1001,3012);
INSERT INTO deck_card (deck_id,card_id) VALUES (1001,3013);
INSERT INTO deck_card (deck_id,card_id) VALUES (1001,3014);
INSERT INTO deck_card (deck_id,card_id) VALUES (1001,3015);
COMMIT TRANSACTION;