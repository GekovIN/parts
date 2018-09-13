USE test;

DROP TABLE IF EXISTS part;
CREATE TABLE part (
  ID INT NOT NULL AUTO_INCREMENT,
  NAME VARCHAR (255) NOT NULL,
  REQUIRED BOOL NOT NULL DEFAULT FALSE,
  QUANTITY INT NOT NULL DEFAULT 0,
  PRIMARY KEY (ID)
);

INSERT INTO test.part VALUES (1, 'Материнская плата', TRUE, 7);
INSERT INTO test.part VALUES (2, 'Процессор', TRUE, 13);
INSERT INTO test.part VALUES (3, 'Звуковая карта', TRUE, 11);
INSERT INTO test.part VALUES (4, 'Видеокарта', TRUE, 9);
INSERT INTO test.part VALUES (5, 'HDD диск', TRUE, 6);
INSERT INTO test.part VALUES (6, 'SSD диск', FALSE , 3);
INSERT INTO test.part VALUES (7, 'Оперативная память', TRUE, 8);
INSERT INTO test.part VALUES (8, 'Корпус', TRUE, 10);
INSERT INTO test.part VALUES (9, 'Подсветка корпуса', FALSE , 2);
INSERT INTO test.part VALUES (10, 'Монитор', FALSE , 5);

INSERT INTO test.part VALUES (11, 'Колонки', FALSE, 5);
INSERT INTO test.part VALUES (12, 'Флеш-карта', FALSE, 25);
INSERT INTO test.part VALUES (13, 'Кард-ридер', FALSE, 2);
INSERT INTO test.part VALUES (14, 'CD привод', FALSE, 10);
INSERT INTO test.part VALUES (15, 'Блок питания', TRUE, 12);
INSERT INTO test.part VALUES (16, 'Внешний жесткий диск', FALSE, 5);
INSERT INTO test.part VALUES (17, 'Клавиатура', FALSE, 29);
INSERT INTO test.part VALUES (18, 'Компьютерная мышь', FALSE, 5);
INSERT INTO test.part VALUES (19, 'Модем', FALSE, 10);
INSERT INTO test.part VALUES (20, 'Принтер', FALSE, 7);


INSERT INTO test.part VALUES (21, 'Test_part_1', FALSE , 5);
INSERT INTO test.part VALUES (22, 'Test_part_2', FALSE , 5);
INSERT INTO test.part VALUES (23, 'Test_part_3', FALSE , 5);
INSERT INTO test.part VALUES (24, 'Test_part_4', FALSE , 5);
INSERT INTO test.part VALUES (25, 'Test_part_5', FALSE , 5);
INSERT INTO test.part VALUES (26, 'Test_part_6', FALSE , 5);
INSERT INTO test.part VALUES (27, 'Test_part_7', FALSE , 5);
INSERT INTO test.part VALUES (28, 'Test_part_8', FALSE , 5);
INSERT INTO test.part VALUES (29, 'Test_part_9', FALSE , 5);
INSERT INTO test.part VALUES (30, 'Test_part_10', FALSE , 5);

INSERT INTO test.part VALUES (31, 'Test_part_11', FALSE , 5);
INSERT INTO test.part VALUES (32, 'Test_part_12', FALSE , 5);
INSERT INTO test.part VALUES (33, 'Test_part_13', FALSE , 5);
INSERT INTO test.part VALUES (34, 'Test_part_14', FALSE , 5);
INSERT INTO test.part VALUES (35, 'Test_part_15', FALSE , 5);
INSERT INTO test.part VALUES (36, 'Test_part_16', FALSE , 5);
INSERT INTO test.part VALUES (37, 'Test_part_17', FALSE , 5);
INSERT INTO test.part VALUES (38, 'Test_part_18', FALSE , 5);
INSERT INTO test.part VALUES (39, 'Test_part_19', FALSE , 5);
INSERT INTO test.part VALUES (40, 'Test_part_20', FALSE , 5);