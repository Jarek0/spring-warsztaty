INSERT INTO item (name, category) VALUES ('Obraz1', 'Obrazy');

INSERT  INTO bid (amount, item_id) VALUES (100, (SELECT id FROM item WHERE name = 'Obraz1'));
INSERT  INTO bid (amount, item_id) VALUES (200, (SELECT id FROM item WHERE name = 'Obraz1'));
INSERT  INTO bid (amount, item_id) VALUES (300, (SELECT id FROM item WHERE name = 'Obraz1'));
INSERT  INTO bid (amount, item_id) VALUES (400, (SELECT id FROM item WHERE name = 'Obraz1'));


INSERT INTO item (name, category) VALUES ('Obraz2', 'Obrazy');

INSERT  INTO bid (amount, item_id) VALUES (100, (SELECT id FROM item WHERE name = 'Obraz2'));
INSERT  INTO bid (amount, item_id) VALUES (200, (SELECT id FROM item WHERE name = 'Obraz2'));


INSERT INTO item (name, category) VALUES ('Obraz7', 'Obrazy');

INSERT  INTO bid (amount, item_id) VALUES (100, (SELECT id FROM item WHERE name = 'Obraz7'));
INSERT  INTO bid (amount, item_id) VALUES (200, (SELECT id FROM item WHERE name = 'Obraz7'));
INSERT  INTO bid (amount, item_id) VALUES (1000, (SELECT id FROM item WHERE name = 'Obraz7'));


INSERT INTO item (name, category) VALUES ('Butelka1', 'Butelki');

INSERT  INTO bid (amount, item_id) VALUES (100, (SELECT id FROM item WHERE name = 'Butelka1'));
INSERT  INTO bid (amount, item_id) VALUES (200, (SELECT id FROM item WHERE name = 'Butelka1'));
INSERT  INTO bid (amount, item_id) VALUES (1000, (SELECT id FROM item WHERE name = 'Butelka1'));

INSERT INTO user_accounts (login, password, role, email) VALUES ('admin', '$2a$10$1hTy42Inio130P3IC.4.x.YKpph0E0X7zncez7AeLvLJtVckyvLqe', 'ROLE_ADMIN', 'admin@gmail.com');
INSERT INTO user_accounts (login, password, role, email) VALUES ('user', '$2a$10$1hTy42Inio130P3IC.4.x.YKpph0E0X7zncez7AeLvLJtVckyvLqe', 'ROLE_USER', 'user@gmail.com');