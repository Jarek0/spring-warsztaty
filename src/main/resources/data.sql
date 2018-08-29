INSERT INTO user_account (city, street, zip_code, login) VALUES ('Zamosc', '1 Maja', '12-12', 'jarek1234');
INSERT INTO personal_data (birth_date, email, first_name, gender, pesel, surname, user_account_id)
VALUES ('1971-07-13', 'jarek1234@o2.pl', 'Jarek', 'MALE', '12312312344', 'Bielec', (SELECT id from user_account WHERE login = 'jarek1234'));

INSERT INTO user_account (city, street, zip_code, login) VALUES ('Lublin', '2 Maja', '12-13', 'andrzej12');
INSERT INTO personal_data (birth_date, email, first_name, gender, pesel, surname, user_account_id)
VALUES ('1971-07-13', 'andrzej12@o2.pl', 'Andrzej', 'MALE', '12312312345', 'Kowalski', (SELECT id from user_account WHERE login = 'andrzej12'));

INSERT INTO user_account (city, street, zip_code, login) VALUES ('Krakow', '2 Maja', '12-13', 'wera12');
INSERT INTO personal_data (birth_date, email, first_name, gender, pesel, surname, user_account_id)
VALUES ('1971-07-13', 'wera12@o2.pl', 'Weronika', 'FAMALE', '13312312345', 'Nowak', (SELECT id from user_account WHERE login = 'wera12'));


INSERT INTO user_account (city, street, zip_code, login) VALUES ('Andrzejow', '2 Maja', '12-13', 'wera13');
INSERT INTO personal_data (birth_date, email, first_name, gender, pesel, surname, user_account_id)
VALUES ('1971-07-13', 'wera13@o2.pl', 'Weronika', 'FAMALE', '13312312355', 'Kowalska', (SELECT id from user_account WHERE login = 'wera13'));

INSERT INTO user_account (city, street, zip_code, login) VALUES ('Zabytow', '2 Maja', '12-13', 'wera15');
INSERT INTO personal_data (birth_date, email, first_name, gender, pesel, surname, user_account_id)
VALUES ('1971-07-13', 'wera15@o2.pl', 'Weronika', 'OTHER', '13317312355', 'Matycz', (SELECT id from user_account WHERE login = 'wera15'));


INSERT INTO user_account (city, street, zip_code, login) VALUES ('Lublin', '7 Maja', '12-13', 'adrian12');
INSERT INTO personal_data (birth_date, email, first_name, gender, pesel, surname, user_account_id)
VALUES ('1971-07-13', 'aadrian12@o2.pl', 'Adrian', 'MALE', '12312312349', 'Nowak', (SELECT id from user_account WHERE login = 'adrian12'));


INSERT INTO user_account (city, street, zip_code, login) VALUES ('Zamosc', '7 Maja', '12-13', 'bartek12');
INSERT INTO personal_data (birth_date, email, first_name, gender, pesel, surname, user_account_id)
VALUES ('1971-07-13', 'bartek12@o2.pl', 'Bartek', 'MALE', '12312312945', 'Nowak', (SELECT id from user_account WHERE login = 'bartek12'));


INSERT INTO user_account (city, street, zip_code, login) VALUES ('Krakow', '7 Maja', '12-13', 'august12');
INSERT INTO personal_data (birth_date, email, first_name, gender, pesel, surname, user_account_id)
VALUES ('1971-07-13', 'august12@o2.pl', 'August', 'MALE', '12312612345', 'Nowak', (SELECT id from user_account WHERE login = 'august12'));


INSERT INTO user_account (city, street, zip_code, login) VALUES ('Lublin', '2 Maja', '12-13', 'lukasz12');
INSERT INTO personal_data (birth_date, email, first_name, gender, pesel, surname, user_account_id)
VALUES ('1971-07-13', 'lukasz12@o2.pl', 'Lukasz', 'MALE', '33312312665', 'Nowak', (SELECT id from user_account WHERE login = 'lukasz12'));