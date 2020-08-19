```sql
-- 1.
select concat(last_name,', ', first_name) from employee_tbl;

-- 2.
select concat(last_name, ', ', first_name), substring(phone, 1,3 ) from employee_tbl;

-- 3.
select concat(first_name, '.', last_name, '@PERPTECH.COM') from employee_tbl;

-- 4.
select * from employee_tbl;

select concat(last_name, ',', first_name) as EMP_NAME, concat(substring(emp_id,1,3), '-', substring(emp_id,4,2), '-',
substring(emp_id,6,4)) as EMP_ID,
concat('(', substring(phone,1,3), ')',substring(phone,4,3), '-', substring(phone,7,4)) as PHONE
from employee_tbl;
```