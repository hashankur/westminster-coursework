-- Q1
SELECT AVG(salary) as "Average Salary" FROM employees
WHERE employee_id BETWEEN 1022 AND 1025;

SELECT AVG(IFNULL(salary, 0)) as "Average Salary" FROM employees
WHERE employee_id BETWEEN 1022 AND 1025;

-- Q2
SELECT
MIN(salary) as "Minimum",
MAX(salary) as "Maximum",
AVG(IFNULL(salary, 0)) as "Average"
FROM employees
WHERE job_id IN (907, 908, 909)
AND hire_date LIKE '2017%';

-- Q3
SELECT
manager_id,
MIN(salary)
FROM employees
WHERE manager_id IS NOT NULL
GROUP BY manager_id
HAVING MIN(salary > 47000);

-- Q4
SELECT
department_id as "Dept Id",
MIN(salary) as "Minimum",
MAX(salary) as "Maximum",
AVG(IFNULL(salary, 0)) as "Average"
FROM employees
WHERE department_id IS NOT NULL
GROUP BY department_id
HAVING MAX(salary) > 50000
AND AVG(IFNULL(salary, 0)) > 50000;

-- Q5
SELECT
d.department_id, d.department_name,
AVG(IFNULL(e.salary, 0))
FROM departments d
JOIN employees e ON d.department_id = e.department_id
GROUP BY department_id;

SELECT
d.department_id, d.department_name,
AVG(IFNULL(e.salary, 0))
FROM departments d
LEFT OUTER JOIN employees e ON d.department_id = e.department_id --check
GROUP BY department_id;