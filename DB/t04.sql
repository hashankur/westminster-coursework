/* 2023-11-17 11:23:19 [4 ms] */ 
SELECT d.department_name, l.city, l.country
FROM locations l JOIN departments d
ON d.location_id = l.location_id
AND l.city = "Seattle" LIMIT 0,100;
/* 2023-11-17 11:24:15 [6 ms] */ 
SELECT * FROM employees LIMIT 0,100;
/* 2023-11-17 11:24:17 [3 ms] */ 
SELECT * FROM departments LIMIT 0,100;
/* 2023-11-17 11:30:20 [4 ms] */ 
SELECT d.department_name, e.first_name, e.last_name, e.salary
FROM departments d JOIN employees e
ON d.department_id = e.employee_id LIMIT 0,100;
/* 2023-11-17 11:30:33 [3 ms] */ 
SELECT d.department_name, e.first_name, e.last_name, e.salary
FROM departments d JOIN employees e
ON d.department_id = e.employee_id
AND e.salary > 10000 LIMIT 0,100;
/* 2023-11-17 11:34:23 [29 ms] */ 
SELECT d.department_name, e.first_name, e.last_name, e.salary
FROM departments d JOIN employees e
ON d.department_id = e.department_id
AND e.first_name LIMIT 0,100;
/* 2023-11-17 11:40:27 [15 ms] */ 
SELECT d.department_name, CONCAT(e.first_name, e.last_name) AS "Full Name", e.salary
FROM departments d JOIN employees e
ON d.department_id = e.department_id
AND NOT d.department_id LIMIT 0,100;
/* 2023-11-17 11:42:01 [4 ms] */ 
SELECT d.department_name, CONCAT(e.first_name, e.last_name) AS "Full Name", e.hire_date, e.salary
FROM departments d JOIN employees e
ON d.department_id = e.department_id
AND NOT d.department_id LIMIT 0,100;
/* 2023-11-17 11:44:57 [4 ms] */ 
SELECT d.department_name, CONCAT(e.first_name, e.last_name) AS "Full Name", e.salary
FROM departments d JOIN employees e
ON d.department_id = e.department_id
AND e.salary > 45000 LIMIT 0,100;
/* 2023-11-17 11:49:13 [3 ms] */ 
SELECT d.department_name, CONCAT(e.first_name, e.last_name) AS "Full Name", e.hire_date, e.salary
FROM departments d JOIN employees e
ON d.department_id = e.department_id
AND NOT e.department_id LIMIT 0,100;
/* 2023-11-17 11:56:30 [3 ms] */ 
SELECT * FROM departments LIMIT 0,100;
/* 2023-11-17 11:56:38 [6 ms] */ 
SELECT * FROM employees LIMIT 0,100;
/* 2023-11-17 11:57:06 [3 ms] */ 
SELECT d.department_name, CONCAT(e.first_name, e.last_name) AS "Full Name", e.hire_date, e.salary
FROM departments d JOIN employees e
ON d.department_id = e.department_id
AND e.department_id = NULL LIMIT 0,100;
/* 2023-11-17 11:57:35 [2 ms] */ 
SELECT d.department_name, CONCAT(e.first_name, e.last_name) AS "Full Name", e.hire_date, e.salary
FROM departments d JOIN employees e
ON d.department_id = e.department_id
AND e.department_id = "" LIMIT 0,100;
/* 2023-11-17 11:58:43 [3 ms] */ 
SELECT d.department_name, CONCAT(e.first_name, e.last_name) AS "Full Name", e.hire_date, e.salary
FROM departments d JOIN employees e
ON d.department_id = e.department_id
AND e.department_id IS NULL LIMIT 0,100;
/* 2023-11-17 12:00:55 [4 ms] */ 
SELECT * FROM employees LIMIT 0,100;
/* 2023-11-17 12:01:23 [4 ms] */ 
SELECT d.department_name, CONCAT(e.first_name, e.last_name) AS "Full Name", e.hire_date, e.salary
FROM departments d JOIN employees e
ON d.department_id = e.department_id
AND d.department_id IS NULL LIMIT 0,100;
/* 2023-11-17 12:01:27 [3 ms] */ 
SELECT d.department_name, CONCAT(e.first_name, e.last_name) AS "Full Name", e.hire_date, e.salary
FROM departments d JOIN employees e
ON d.department_id = e.department_id
AND e.department_id IS NULL LIMIT 0,100;
/* 2023-11-17 12:17:22 [12 ms] */ 
SELECT e.last_name, e.salary, j.job_title
FROM employees e JOIN jobs j
ON e.job_id = j.job_id
JOIN departments d
ON e.department_id = d.department_id and d.department_name = "IT" LIMIT 0,100;
/* 2023-11-17 12:18:53 [2 ms] */ 
SELECT d.department_name, CONCAT(e.first_name, e.last_name) AS "Full Name", e.salary
FROM departments d JOIN employees e
ON d.department_id = e.department_id
AND e.salary > 45000 LIMIT 0,100;
/* 2023-11-17 12:19:15 [1 ms] */ 
SELECT d.department_name, CONCAT(e.first_name, e.last_name) AS "Full Name", e.hire_date, e.salary
FROM departments d JOIN employees e
ON d.department_id = e.department_id
AND e.department_id IS NULL LIMIT 0,100;
/* 2023-11-17 12:27:46 [2 ms] */ 
SELECT d.department_name, CONCAT(e.first_name, e.last_name) AS "Full Name", e.hire_date, e.salary
FROM departments d RIGHT OUTER JOIN employees e
ON d.department_id = e.department_id LIMIT 0,100;
/* 2023-11-17 12:29:02 [2 ms] */ 
SELECT d.department_name, CONCAT(e.first_name, e.last_name) AS "Full Name", e.hire_date, e.salary
FROM departments d LEFT OUTER JOIN employees e
ON d.department_id = e.department_id LIMIT 0,100;
/* 2023-11-17 15:11:06 [21 ms] */ 
SELECT * FROM employees LIMIT 0,100;
