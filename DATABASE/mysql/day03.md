
# day03

## 建表语句：

```sql
-- CREATE EMPLOYEE_TBL
CREATE TABLE EMPLOYEE_TBL
(
    EMP_ID VARCHAR(9) NOT NULL,
    LAST_NAME VARCHAR(15) NOT NULL,
    FIRST_NAME VARCHAR(15) NOT NULL,
    MIDDLE_NAME VARCHAR(15),
    ADDRESS VARCHAR(30) NOT NULL,
    CITY VARCHAR(15) NOT NULL,
    STATE CHAR(2) NOT NULL,
    ZIP INTEGER(5) NOT NULL,
    PHONE CHAR(10),
    PAGER CHAR(10),
    CONSTRAINT EMP_PK PRIMARY KEY (EMP_ID)
);

-- CREATE EMPLOYEE_PAY_TBL
CREATE TABLE EMPLOYEE_PAY_TBL
(
    EMP_ID VARCHAR(9) NOT NULL primary key,
    POSITION VARCHAR(15) NOT NULL,
    DATE_HIRE DATE,
    PAY_RATE DECIMAL(4,2),
    DATE_LAST_RAISE DATE,
    SALARY DECIMAL(8,2),
    BONUS DECIMAL(6,2),
    CONSTRAINT EMP_FK FOREIGN KEY (EMP_ID) REFERENCES EMPLOYEE_TBL (EMP_ID)
);

-- CUSTOMER_TBL
CREATE TABLE CUSTOMER_TBL
(
    CUST_ID VARCHAR(10) NOT NULL primary key,
    CUST_NAME VARCHAR(30) NOT NULL,
    CUST_ADDRESS VARCHAR(20) NOT NULL,
    CUST_CITY VARCHAR(15) NOT NULL,
    CUST_STATE CHAR(2) NOT NULL,
    CUST_ZIP INTEGER(5) NOT NULL,
    CUST_PHONE CHAR(10),
    CUST_FAX INTEGER(10)
);

-- ORDERS_TBL
CREATE TABLE ODERS_TBL
(
    ORD_NUM VARCHAR(10) NOT NULL primary key,
    CUST_ID VARCHAR(10) NOT NULL,
    PROD_ID VARCHAR(10) NOT NULL,
    QTY INTEGER(6) NOT NULL,
    ORD_DATE DATE
);

-- PRODUCTS_TBL
CREATE TABLE PRODUCTS_TBL
(
    PROD_ID VARCHAR(10) NOT NULL primary key,
    PROD_DESC VARCHAR(40) NOT NULL,
    COST DECIMAL(6,2) NOT NULL
);
```

## 创建完5张表：

```s
mysql> show tables;
+--------------------+
| Tables_in_learnsql |
+--------------------+
| CUSTOMER_TBL       |
| EMPLOYEE_PAY_TBL   |
| EMPLOYEE_TBL       |
| ODERS_TBL          |
| PRODUCTS_TBL       |
+--------------------+
5 rows in set (0.00 sec)
```

## 常用语句

- 列出所有表：

show tables;

- 列出表的全部字段和它们的属性：

`describe employee_tbl;`

`desc employee_pay_tbl;`

```s
mysql> describe employee_pay_tbl;
+-----------------+--------------+------+-----+---------+-------+
| Field           | Type         | Null | Key | Default | Extra |
+-----------------+--------------+------+-----+---------+-------+
| EMP_ID          | varchar(9)   | NO   | PRI | NULL    |       |
| POSITION        | varchar(15)  | NO   |     | NULL    |       |
| DATE_HIRE       | date         | YES  |     | NULL    |       |
| PAY_RATE        | decimal(4,2) | YES  |     | NULL    |       |
| DATE_LAST_RAISE | date         | YES  |     | NULL    |       |
| SALARY          | decimal(8,2) | YES  |     | NULL    |       |
| BONUS           | decimal(6,2) | YES  |     | NULL    |       |
+-----------------+--------------+------+-----+---------+-------+
7 rows in set (0.01 sec)
```

- 删除表

`drop table orders_tbl;`
