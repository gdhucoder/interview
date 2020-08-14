```sql
select * from employee_pay_tbl;

select avg(salary) from employee_pay_tbl;

select max(bonus) from employee_pay_tbl;

select sum(salary) from employee_pay_tbl;

select min(salary) from employee_pay_tbl;

select min(pay_rate) from employee_pay_tbl;

select count(*) from employee_pay_tbl;

select * from employee_tbl;

-- name starts with 'G'
select count(*) from employee_tbl t where t.first_name like 'G%';

-- 
select * from orders_tbl;

select sum(qty*10) as total from orders_tbl;


--
select * from employee_tbl t
order by t.first_name;

select avg(first_name) from employee_tbl;
-- 系统按照排序规则
select min(last_name) from employee_tbl;

select max(last_name) from employee_tbl;
```