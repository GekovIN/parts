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