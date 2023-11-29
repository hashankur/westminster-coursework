/* 2023-11-10 11:40:21 [4 ms] */ 
SELECT * FROM employees LIMIT 0,100;
/* 2023-11-10 11:40:32 [3 ms] */ 
SELECT * FROM locations LIMIT 0,100;
/* 2023-11-10 11:40:39 [4 ms] */ 
SELECT * FROM employees LIMIT 0,100;
/* 2023-11-10 11:42:04 [6 ms] */ 
SELECT * FROM employees WHERE salary > 40000 LIMIT 0,100;
/* 2023-11-10 11:46:21 [5 ms] */ 
SELECT * FROM employees WHERE salary BETWEEN 40000 AND 50000 LIMIT 0,100;
/* 2023-11-10 11:48:49 [4 ms] */ 
SELECT * FROM employees WHERE salary NOT BETWEEN 40000 AND 50000 LIMIT 0,100;
/* 2023-11-10 11:49:39 [5 ms] */ 
SELECT last_name, salary FROM employees WHERE salary NOT BETWEEN 40000 AND 50000 LIMIT 0,100;
/* 2023-11-10 11:54:01 [3 ms] */ 
SELECT last_name, salary FROM employees WHERE salary NOT BETWEEN 40000 AND 75000 LIMIT 0,100;
/* 2023-11-10 11:57:55 [3 ms] */ 
SELECT last_name, hire_date, department_id FROM employees WHERE last_name = "Matos" LIMIT 0,100;
/* 2023-11-10 12:00:08 [5 ms] */ 
SELECT last_name, hire_date, salary FROM employees WHERE hire_date < "2016-01-01" LIMIT 0,100;
/* 2023-11-10 12:00:23 [4 ms] */ 
SELECT last_name, hire_date, salary FROM employees WHERE hire_date > "2016-01-01" LIMIT 0,100;
/* 2023-11-10 12:04:32 [5 ms] */ 
SELECT * FROM employees WHERE department_id IN (10, 20) LIMIT 0,100;
/* 2023-11-10 12:09:22 [3 ms] */ 
SELECT last_name, job_id, hire_date FROM employees 
WHERE last_name in ("Matos", "Taylor")
ORDER BY hire_date DESC LIMIT 0,100;
/* 2023-11-10 12:15:03 [6 ms] */ 
SELECT last_name, salary, department_id FROM employees
WHERE salary BETWEEN 37000 AND 57000
AND department_id IN (20, 40) LIMIT 0,100;
/* 2023-11-10 12:18:17 [4 ms] */ 
SELECT last_name, job_id, hire_date from employees
WHERE department_id = 40
AND salary > 41000
AND hire_date < "2016-02-15" LIMIT 0,100;
/* 2023-11-10 12:26:09 [14 ms] */ 
SELECT last_name FROM employees
WHERE SUBSTRING(last_name, 3, 3) = "a" LIMIT 0,100;
/* 2023-11-10 12:26:19 [3 ms] */ 
SELECT last_name FROM employees
WHERE SUBSTRING(last_name, 2, 3) = "a" LIMIT 0,100;
/* 2023-11-10 12:29:02 [4 ms] */ 
SELECT last_name AS 'Employee', salary AS 'Yearly Salary', department_id AS 'DepId'
FROM employees
WHERE salary BETWEEN 37000 AND 57000
AND department_id IN (20, 40) LIMIT 0,100;
