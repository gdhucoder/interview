operator

in: 把一个值和一个指定列表进行比较。当至少有一个值与列表中的一个值匹配时，返回true。

使用in与or可以得到同样的结果，但in更快。

like：百分号%表示零个，一个或者多个字符。 下划线（_）代表一个数字或者字符。

exists：搜索指定表里是否存在满足特定条件的记录。

例如：

```sql
select cost from products_tbl where exists (select cost from products_tbl where cost<10)

select cust_id, cust_name from customer_tbl
where cust_city in (upper('INDIANAPOLIS'))
and (cust_name like 'M%' or cust_name like 'R%');


select prod_id, prod_desc, cost from products_tbl
where cost between 1.00 and 12.50;

select prod_id, prod_desc, cost from products_tbl
where cost >= 1.00 and cost<=7.00;

select prod_id, prod_desc, cost from products_tbl
where cost < 1.00 or cost > 12.5;

select prod_id, prod_desc, cost from products_tbl
where cost not between 1.00 and 12.50;


select prod_id, prod_desc, cost*0.05 as tax from products_tbl
order by cost desc;

select prod_id, prod_desc, cost, cost*0.05 as tax, (1+0.05)*cost as total from products_tbl
order by cost desc;

-- 9
select * from products_tbl where prod_desc like 'P%';

select * from products_tbl where prod_desc not like 'P%';
```