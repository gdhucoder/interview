```sql

-- 2
select city, count(*) 
from employee_tbl
group by city;

-- 3
select city, count(*)
from employee_tbl
group by city
having count(*) > 1;

-- 4 
select city, count(*)
from employee_tbl
group by city
order by count(*) desc;

-- 5
select * from employee_pay_tbl;

select e.city, avg(p.salary)
from employee_tbl e, employee_pay_tbl p
where e.emp_id = p.emp_id
group by e.city;

-- 6 
select e.city, avg(p.salary) as avg_sal
from employee_tbl e, employee_pay_tbl p
where e.emp_id = p.emp_id
group by e.city
having avg_sal > 20000;
```