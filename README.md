
## Employment Management System (EMS)

A Spring Boot backend application to manage employees, departments, and addresses.
This project showcases enterprise-grade backend development with a strong emphasis on clean architecture, DTO-based API design, validations, exception handling, and maintainability.


## Overview
The Employee Management System is a Spring Boot 3 backend application built to manage employees, departments, and their addresses. It follows a clean layered architecture with DTO-based API design, validation, centralized exception handling, and production-ready features like Swagger API docs, Actuator monitoring, and structured logging.

This project demonstrates:

Employee lifecycle management (CRUD operations).

Department integration and employee assignment.

Address management linked to employees.

Business rule validations (age, email, phone).

Error handling & ResponseEntity-based API design.

Paging, sorting, and database mapping with JPA/Hibernate.

It is designed with scalability, maintainability, and enterprise best practices in mind, making it an ideal showcase for backend development skills.

# Employee Management System (EMS)

# Employee Management System (EMS) 
![Java](https://img.shields.io/badge/Java-brightgreen?logo=java&logoColor=white) 
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-brightgreen?logo=spring&logoColor=white) 
![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-blue?logo=spring&logoColor=white) 
![Swagger](https://img.shields.io/badge/Swagger-UI-orange?logo=swagger&logoColor=white) 
![MySQL](https://img.shields.io/badge/MySQL-blue?logo=mysql&logoColor=white) 
![Maven](https://img.shields.io/badge/Maven-red?logo=apache-maven&logoColor=white) 
![Git](https://img.shields.io/badge/Git-F05032?logo=git&logoColor=white) 
![GitHub](https://img.shields.io/badge/GitHub-181717?logo=github&logoColor=white) 
![Actuator](https://img.shields.io/badge/Spring%20Boot%20Actuator-available-brightgreen) 
![SLF4J+Logback](https://img.shields.io/badge/Logging-SLF4J%2BLogback-lightgrey)



## Key Technologies
Java Spring Boot (Backend): A powerful, established Java framework for building production-ready applications with Java, offering robust backend support and data management.
Spring Data JPA: A part of the Spring Data project that makes it easy to implement JPA-based repositories.
Spring Boot Actuator: A set of production-ready features that help monitor and manage the application.
Spring Boot Starter Web: A starter for building web applications, including RESTful, application, and WebSocket services.
Spring Boot Starter Data JPA: A starter for using Spring Data JPA with Hibernate.
RESTful APIs: Representational state transfer (REST) APIs for communication between the frontend and backend.
Java 21: The latest LTS version of Java, providing long-term support and stability.
Swagger: A tool for documenting and testing RESTful APIs.
## Key Features
👨‍💼 Employee Management – Create, update, fetch, and delete employees.

🏢 Department Integration – Assign employees to departments.

📍 Address Handling – Manage multiple addresses per employee.

✅ Validation Rules –

Age must be between 18 and 65

Email must follow valid format

Mobile number must follow valid format

🚫 Exception Handling – Centralized error handling with user-friendly responses.

📑 Paging & Sorting – Efficient retrieval of large datasets.

📖 Swagger Integration – Interactive API documentation.

📊 Spring Boot Actuator – Application monitoring and health checks.

📝 Logging – Structured application logging with SLF4J + Logback.

⚡ DevTools – Hot reload for faster development.

🔐 Future Scope – Authentication & authorization with JWT, Docker deployment, payroll module.
## Project Structure
Employee-Management-System/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── ems/
│   │   │           └── project/
│   │   │               ├── EmployeeManagementSystemApplication.java
│   │   │
│   │   │               ├── controller/
│   │   │               │   ├── EmpController.java
│   │   │               │   ├── DepartmentsController.java
│   │   │               │   ├── AddressController.java
│   │   │               │   ├── EmpDeptDetailsController.java
│   │   │               │   ├── JobController.java
│   │   │               │   └── SalaryController.java
│   │   │
│   │   │               ├── dto/
│   │   │               │   ├── EmpDto.java
│   │   │               │   ├── DeptDto.java
│   │   │               │   ├── AddressDto.java
│   │   │               │   ├── DeptUpdateDto.java
│   │   │               │   ├── EmpDeptDetailsDto.java
│   │   │               │   ├── EmpMainDetailsDto.java
│   │   │               │   ├── JobDto.java
│   │   │               │   └── SalaryDto.java
│   │   │
│   │   │               ├── entity/
│   │   │               │   ├── Employee.java
│   │   │               │   ├── Dept.java
│   │   │               │   ├── Address.java
│   │   │               │   ├── EmpDeptDetails.java
│   │   │               │   ├── Job.java
│   │   │               │   └── Salary.java
│   │   │
│   │   │               ├── exception/
│   │   │               │   ├── ExceptionAdviceController.java
│   │   │               │   ├── DepartmentDetailsAlreadyExistsException.java
│   │   │               │   ├── EmployeeDetailsAlreadyExistException.java
│   │   │               │   └── ResourceNotFoundException.java
│   │   │
│   │   │               ├── repository/
│   │   │               │   ├── EmployeeRepository.java
│   │   │               │   ├── DeptRepository.java
│   │   │               │   ├── AddressRepository.java
│   │   │               │   ├── EmpDeptDetailsRepository.java
│   │   │               │   ├── JobRepository.java
│   │   │               │   └── SalaryRepository.java
│   │   │
│   │   │               └── service/
│   │   │                   ├── EmpService.java
│   │   │                   ├── DepartmentsService.java
│   │   │                   ├── AddressService.java
│   │   │                   ├── EmpDeptDetailsService.java
│   │   │                   ├── JobService.java
│   │   │                   └── SalaryService.java
│   │   │
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       └── templates/
│   │
│   └── test/
│       └── java/
│           └── com/
│               └── ems/
│                   └── project/
│                       └── EmployeeManagementSystemApplicationTests.java
├── .gitignore
├── pom.xml
└── README.md

## API Endpoints

<img width="1844" height="778" alt="Screenshot 2025-09-16 100111" src="https://github.com/user-attachments/assets/aaf7e0f1-bad5-4de0-9988-80bebf3df496" />

<img width="1830" height="767" alt="Screenshot 2025-09-16 100125" src="https://github.com/user-attachments/assets/2e58dfbe-a5cd-4cc6-a005-23e143174fd5" />

<img width="1844" height="435" alt="Screenshot 2025-09-16 100135" src="https://github.com/user-attachments/assets/e7fd7c36-1ba3-4ccd-9e29-a8d8f51ff594" />

<img width="1821" height="446" alt="Screenshot 2025-09-16 100145" src="https://github.com/user-attachments/assets/1acad69c-7721-4666-a4a4-23abb3c08913" />

<img width="1846" height="584" alt="Screenshot 2025-09-16 100156" src="https://github.com/user-attachments/assets/a0eebc57-c2a4-4f46-9f62-26cba1493f46" />

<img width="1839" height="828" alt="Screenshot 2025-09-16 100210" src="https://github.com/user-attachments/assets/f2651884-c470-46b0-b2ab-5d13ecf13489" />

## Project Setup
1. Prerequisites

Ensure that you have Java 21 installed on your local machine. If not, follow the instructions below:

For MacOS:
            brew install openjdk@21
export JAVA_HOME=/usr/local/opt/openjdk@21

For Windows:
Download OpenJDK 21 from https://jdk.java.net/21/
 and follow the installation instructions.

Also, ensure MySQL is installed and running on your local machine.

2. Clone the Repository
git clone https://github.com/kesavan-av21/Employee-Management-System.git
cd Employee-Management-System

3. Install Dependencies

Ensure Maven is installed on your system.

For MacOS:

brew install maven


For Windows:
Download Maven from https://maven.apache.org/download.cgi
 and follow the installation instructions.

Then, install project dependencies:

mvn install

4. Configure the Application

Update src/main/resources/application.properties with your MySQL configuration:

 # MySQL Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/ems_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
You can change the database name, username, and password as per your setup.

5. Start the Backend Server

Before starting the server, ensure MySQL is running. Then, start the Spring Boot application:

mvn spring-boot:run


The backend will be available at:

http://localhost:8080

6. Access the API Endpoints

Example endpoints:

Get all employees:

curl -X GET http://localhost:8080/api/employees


Get employee by ID:

curl -X GET http://localhost:8080/api/employees/1


Get all departments:

curl -X GET http://localhost:8080/api/departments


Get department by ID:

curl -X GET http://localhost:8080/api/departments/1


You can explore other endpoints like Address, Jobs, Salary, and EmpDeptDetails similarly.

7. API Documentation (Swagger)

This project uses Swagger UI to provide interactive API documentation.

How to Access Swagger UI:

Start the backend server:

mvn spring-boot:run


Open the Swagger interface in your browser:

http://localhost:8080/swagger-ui.html


Alternatively, access the raw OpenAPI JSON:

http://localhost:8080/v3/api-docs
Benefits of Swagger UI:

Interactive documentation for all endpoints

Quick testing directly from the browser

Clear communication of request methods, parameters, and responses

Once set up correctly, you can explore all Employee, Department, Address, Job, Salary, and EmpDeptDetails APIs directly via Swagger UI.
## Contributing
If you'd like to contribute to the project, please fork the repository and submit a pull request with your changes. Ensure that you follow the project's coding standards and include relevant tests for new features.
## Future Scope
JWT-based authentication & authorization
Docker deployment for easier setup
Payroll management module
Frontend integration (React/Angular)