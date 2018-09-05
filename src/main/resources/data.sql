INSERT INTO item (name, category) VALUES ('obraz1', 'obrazy');

INSERT  INTO bid (amount, item_id) VALUES (100, (SELECT id FROM item WHERE name = 'obraz1'));
INSERT  INTO bid (amount, item_id) VALUES (200, (SELECT id FROM item WHERE name = 'obraz1'));
INSERT  INTO bid (amount, item_id) VALUES (300, (SELECT id FROM item WHERE name = 'obraz1'));
INSERT  INTO bid (amount, item_id) VALUES (400, (SELECT id FROM item WHERE name = 'obraz1'));


INSERT INTO item (name, category) VALUES ('obraz2', 'obrazy');

INSERT  INTO bid (amount, item_id) VALUES (100, (SELECT id FROM item WHERE name = 'obraz2'));
INSERT  INTO bid (amount, item_id) VALUES (200, (SELECT id FROM item WHERE name = 'obraz2'));


INSERT INTO item (name, category) VALUES ('obraz7', 'obrazy');

INSERT  INTO bid (amount, item_id) VALUES (100, (SELECT id FROM item WHERE name = 'obraz7'));
INSERT  INTO bid (amount, item_id) VALUES (200, (SELECT id FROM item WHERE name = 'obraz7'));
INSERT  INTO bid (amount, item_id) VALUES (1000, (SELECT id FROM item WHERE name = 'obraz7'));


INSERT INTO item (name, category) VALUES ('butelka1', 'butelki');

INSERT  INTO bid (amount, item_id) VALUES (100, (SELECT id FROM item WHERE name = 'butelka1'));
INSERT  INTO bid (amount, item_id) VALUES (200, (SELECT id FROM item WHERE name = 'butelka1'));
INSERT  INTO bid (amount, item_id) VALUES (1000, (SELECT id FROM item WHERE name = 'butelka1'));