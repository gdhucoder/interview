
```sql
select * from employee_tbl t where upper(t.FIRST_NAME) = upper('Linda');

select * from products_tbl;

select prod_desc, cost from products_tbl order by 2 desc;

select * from customer_tbl;

select t.cust_name, t.cust_phone from customer_tbl t;

select t.cust_name, t.cust_phone from customer_tbl t
where t.cust_name = upper('ryans stuff');

```