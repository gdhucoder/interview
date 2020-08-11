# 第5章

```sql
CREATE TABLE product_tbl
(
    PROD_ID VARCHAR(10) NOT NULL,
    PROD_DESC VARCHAR(25) NOT NULL,
    COST DECIMAL(6,2) NOT NULL
);

INSERT INTO product_tbl
VALUES ('775', 'a lion', 22.56); -- 数值不必使用单引号

INSERT INTO product_tbl
VALUES ('7755', 'a lion', '99.99');  -- 使用单引号也会转换成数字

INSERT INTO ODERS_TBL(ord_num, cust_id, prod_id, qty, ord_date)
VALUES ('123A1', '109', '123', 23, NULL);

INSERT INTO PRODUCTS_TBL(PROD_ID, PROD_DESC, COST)
VALUES('123', 'a product', 10.123);

INSERT INTO PRODUCTS_TBL(PROD_ID, PROD_DESC, COST)
VALUES('124', 'a product 124', 10.6666); -- 四舍五入

CREATE TABLE PRODUCTS_TMP
(
    PROD_ID VARCHAR(10) NOT NULL primary key,
    PROD_DESC VARCHAR(40) NOT NULL,
    COST DECIMAL(6,2) NOT NULL,
    ATTRIBUTE1 VARCHAR(20) NULL
);

-- 向另一个表中插入 select 出的数据，前提是这个表存在，且列要匹配上
insert into products_tmp(PROD_ID, PROD_DESC, COST)
select * from products_tbl;

mysql> select * from products_tmp;
+---------+---------------+-------+------------+
| PROD_ID | PROD_DESC     | COST  | ATTRIBUTE1 |
+---------+---------------+-------+------------+
| 123     | a product     | 10.12 | NULL       |
| 124     | a product 124 | 10.67 | NULL       |
+---------+---------------+-------+------------+
2 rows in set (0.00 sec)

```
```sql
insert into PRODUCTS_TBL (PROD_ID, PROD_DESC, COST)
values ('301', 'FIREMAN COSTUME', 24.99);

insert into PRODUCTS_TBL (PROD_ID, PROD_DESC, COST)
values ('302', 'PLICEMAN COSTUME', 24.99);

insert into PRODUCTS_TBL (PROD_ID, PROD_DESC, COST)
values ('303', 'KIDDIE GRAB BAG', 4.99);

-- OK

update PRODUCTS_TBL
set COST = 29.99 -- (select COST from PRODUCTS_TBL where PROD_DESC = 'WITCH COSTUME')
where PROD_ID in ('301', '302');

-- 07:41:34	update PRODUCTS_TBL set COST = (select COST from PRODUCTS_TBL where PROD_DESC = 'WITCH COSTUME') where PROD_ID in ('301', '302')	Error Code: 1093. You can't specify target table 'PRODUCTS_TBL' for update in FROM clause	0.0024 sec

delete from PRODUCTS_TBL t where t.PROD_ID in ('301', '302', '303');

```

