
# 第6 章 管理数据库事务

什么是事务：

事务是对数据库执行的一个操作单位。

- 所有事务都有开始和结束
- 事务可以背撤销或者保存
- 如果事务在执行途中是吧，事务中的任何部分都不会被记录到数据库

```
CREATE TABLE PRODUCTS_TMP
(
	PROD_ID			VARCHAR(10)		NOT NULL		PRIMARY KEY,
	PROD_DESC		VARCHAR(40)		NOT NULL,
	COST			DECIMAL(6,2)	NOT NULL
);
```

关于MySQL事务： https://dev.mysql.com/doc/refman/5.6/en/innodb-autocommit-commit-rollback.html

默认情况下 MySQL 服务器是打开autocommit的，会自动提交执行的每条语句。

如果要执行多笔事务，要这么写：

```sql
mysql> SET autocommit=0;
Query OK, 0 rows affected (0.00 sec)
mysql> INSERT INTO customer VALUES (15, 'John');
Query OK, 1 row affected (0.00 sec)
mysql> INSERT INTO customer VALUES (20, 'Paul');
Query OK, 1 row affected (0.00 sec)
mysql> DELETE FROM customer WHERE b = 'Heikki';
Query OK, 1 row affected (0.00 sec)
mysql> -- Now we undo those last 2 inserts and the delete.
mysql> ROLLBACK;
```

或者保持autocommit开启

显示使用：START TRANSACTION或者BEGIN，使用COMMIT提交事务

```sql
BEGIN;
update PRODUCTS_TMP
set cost = 1020
where PROD_ID = '11235';
--ROLLBACK;
COMMIT;
```