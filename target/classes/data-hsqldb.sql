DELETE FROM bankstatements;
DELETE FROM accounts;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name)
VALUES ('Иванов Иван'),
       ('Петров Петр'),
       ('Сидоров Сидр');

INSERT INTO accounts (number, description, typeCurrency, value, user_id)
VALUES (11, 'Рубл.счет (Иванов)', 'RUB', 173000.12, 100000),
       (22, 'Вал.счет (Иванов $)', 'USD', 525, 100000),
       (33, 'Вал.счет (Иванов)', 'EUR', 317, 100000),
       (44, 'Рубл.счет (Петров)', 'RUB', 281958, 100001),
       (55, 'Рубл.счет (Сидоров)', 'RUB', 125001, 100002),
       (66, 'Вал.счет (Сидоров)', 'EUR', 100, 100002)
;
INSERT INTO bankstatements (description, typeCurrency, value, user_id, account_id)
VALUES ('Иванов поступление руб.', 'RUB', 17.12, 100000, 100003),
       ('Иванов поступление $.', 'USD', 222, 100000, 100004),
       ('Петров поступление руб.', 'RUB', 333, 100001, 100006),
       ('Сидоров поступление евро.', 'EUR', 444, 100002, 100008)
;

