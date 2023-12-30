DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS goods;

CREATE TABLE goods (
  goodsId INT NOT NULL,
  goodsName VARCHAR(255) NOT NULL,
  price DOUBLE NOT NULL,
  PRIMARY KEY (goodsId)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb3;

INSERT  INTO goods(goodsId,goodsName,price) VALUES (1001,'book',7),
						(1002,'rubber',10),
						(1003,'pencil',1);

CREATE TABLE orders (
  ordersId INT NOT NULL,
  num INT NOT NULL,
  goodsId INT NOT NULL,
  TIME DATE NOT NULL,
  price DOUBLE NOT NULL,
  PRIMARY KEY (ordersId),
  KEY goodsId (goodsId),
  CONSTRAINT orders_FK FOREIGN KEY (goodsId) REFERENCES goods (goodsId)
) ENGINE=INNODB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;

INSERT  INTO orders(ordersId,num,goodsId,TIME,price) VALUES (101,4,1001,'2023-12-16',28),
							(102,6,1003,'2011-11-12',6),
							(103,2,1002,'2022-01-11',20),
							(104,6,1003,'2011-11-12',6);