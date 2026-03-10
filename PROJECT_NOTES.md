# Java DB Practice — Project Notes

## Overview
Two Spring Boot projects demonstrating two different approaches 
to database interaction in Java.

---

## Project 1 — Spring JDBC (spring-jdbc-demo)

### What it does
Employee CRUD operations using Spring JDBC (JdbcTemplate).
Manually written SQL queries executed against MySQL database.

### Tech Stack
- Java 21
- Spring Boot 3.5.11
- Spring JDBC (JdbcTemplate)
- MySQL 8
- Maven

### Project Flow
1. User sends POST /employees with JSON body
2. EmployeeController receives request via @PostMapping
3. Controller calls repo.insertEmployee(e)
4. EmployeeRepository uses JdbcTemplate to run:
   INSERT INTO employee (name, department) VALUES (?, ?)
5. Returns "1 record inserted!"

1. User sends GET /employees
2. EmployeeController receives request via @GetMapping
3. Controller calls repo.getAllEmployees()
4. JdbcTemplate runs: SELECT * FROM employee
5. RowMapper maps each row to Employee object
6. Returns list of employees as JSON

### Key Classes
- Employee.java — Plain Java model class (POJO)
- EmployeeRepository.java — Contains JdbcTemplate logic
- EmployeeController.java — REST endpoints
- SpringJdbcDemoApplication.java — Entry point

### Interview Points
- JdbcTemplate eliminates boilerplate JDBC code
- You write SQL manually — full control over queries
- update() method for INSERT/UPDATE/DELETE
- query() method with lambda RowMapper for SELECT
- Good for complex queries and legacy databases

---

## Project 2 — Spring Data JPA (spring-jpa-demo)

### What it does
Employee CRUD operations using Spring Data JPA.
Hibernate ORM auto-generates SQL — no manual SQL needed.

### Tech Stack
- Java 21
- Spring Boot 3.5.11
- Spring Data JPA (Hibernate ORM)
- MySQL 8
- Maven

### Project Flow
1. User sends POST /employees with JSON body
2. EmployeeController receives request via @PostMapping
3. Controller calls repo.save(e)
4. JpaRepository auto-generates:
   INSERT INTO employee (department, name) VALUES (?, ?)
5. Returns full saved Employee object with generated ID

1. User sends GET /employees
2. EmployeeController receives request via @GetMapping
3. Controller calls repo.findAll()
4. Hibernate auto-generates: SELECT * FROM employee
5. Returns list of employees as JSON

### Key Classes
- Employee.java — @Entity class mapped to employee table
- EmployeeRepository.java — Interface extending JpaRepository
- EmployeeController.java — REST endpoints
- SpringJpaDemoApplication.java — Entry point with @EntityScan

### JPA Annotations Used
- @Entity — marks class as database table
- @Table(name="employee") — maps to table name
- @Id — marks primary key
- @GeneratedValue(strategy=IDENTITY) — auto increment
- @Column — maps field to column

### Interview Points
- JpaRepository provides save(), findAll(), findById(), 
  deleteById() for free — no implementation needed
- Hibernate auto-generates SQL based on entity mapping
- ddl-auto=update means Hibernate auto-manages table structure
- show-sql=true prints generated SQL to console
- Good for standard CRUD and rapid development

---

## Difference Between Both Approaches

| Feature           | Spring JDBC        | Spring Data JPA      |
|-------------------|--------------------|----------------------|
| SQL               | Written manually   | Auto-generated       |
| Repository        | Class              | Interface only       |
| Return on insert  | Row count (int)    | Saved object         |
| ORM               | No                 | Yes (Hibernate)      |
| Control           | Full               | Abstracted           |
| Best for          | Complex queries    | Standard CRUD        |

---

## API Endpoints (Both Projects)

POST /employees
Request body: { "name": "Abethnego", "department": "Engineering" }
JDBC Response: "1 record inserted!"
JPA Response:  { "id": 2, "name": "Abethnego", "department": "Engineering" }

GET /employees
Response: [{"id":1,"name":"Abethnego","department":"Engineering"}]

---

## How to Run

1. Start MySQL:
   brew services start mysql

2. Run Spring JDBC:
   cd spring-jdbc-demo
   mvn spring-boot:run

3. Run Spring Data JPA:
   cd spring-jpa-demo
   mvn spring-boot:run

4. Test with curl:
   curl -X POST http://localhost:8080/employees \
     -H "Content-Type: application/json" \
     -d '{"name":"Abethnego","department":"Engineering"}'

   curl http://localhost:8080/employees

---

## Author
Abethnego S
Java Developer Trainee @ Hulkhire Tech Pvt. Ltd.
GitHub: github.com/Abeth23/java-db-practise
