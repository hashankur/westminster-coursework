SELECT * FROM employees;
SELECT * FROM departments;
SELECT d.department_name, l.city, l.country
FROM locations l JOIN departments d
ON d.location_id = l.location_id
AND l.city = "Seattle";

SELECT d.department_name, CONCAT(e.first_name, e.last_name) AS "Full Name", e.salary
FROM departments d JOIN employees e
ON d.department_id = e.department_id
AND e.salary > 45000;

-- Q7
SELECT e.last_name, e.salary, j.job_title
FROM employees e JOIN jobs j
ON e.job_id = j.job_id
JOIN departments d
ON e.department_id = d.department_id and d.department_name = "IT";

-- Q11
SELECT d.department_name, CONCAT(e.first_name, e.last_name) AS "Full Name", e.hire_date, e.salary
FROM departments d LEFT OUTER JOIN employees e
ON d.department_id = e.department_id;

-- Q12
SELECT d.department_name, CONCAT(e.first_name, " ", e.last_name) AS "Full Name", e.hire_date, e.salary
FROM departments d RIGHT OUTER JOIN employees e
ON d.department_id = e.department_id;
