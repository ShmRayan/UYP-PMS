# UYP-PMS (Umelaphi Yaodian Pharmacy Prescription Management System) 

### Group Members 
- Rayan Saadani Hassani – 300289736  

---

## 1. Project Overview

The **Umelaphi Yaodian Pharmacy Prescription Management System (UYP-PMS)** is a web-based application designed to support pharmacy personnel in managing patients and prescriptions.  
The system implements:

- **Domain-Driven Design (DDD)**
- **Spring Boot** for the application layer
- **Thymeleaf** for the UI
- **MySQL** for persistence
- **Docker** for deployment


---

## 3. Roles and Permissions

The system defines **three roles**, each with specific permissions.

### Administrator
- Manage pharmacy agents  
- Register / update / deactivate accounts  
- **Cannot** access patients, prescriptions, or reports  

### Pharmacist
- Manage patients  
- Create prescriptions  
- Verify prescriptions  
- Generate drug usage reports  

### Assistant
- Create prescriptions  
- Prepare prescriptions  
- Deliver prescriptions after verification  
- **Cannot** register patients  
- **Cannot** verify prescriptions  
- **Cannot** generate reports  


---

## 4. Application Architecture (DDD)

The repository follows a **DDD layered architecture**:

### Domain Layer
- Core business logic  
- Entities: `Patient`, `Prescription`
- Value objects (IDs, statuses, etc.)  
- Domain services (ex: external registry verification)

### Application Layer
- Use case interfaces  
- Commands (input models)  
- Use case service implementations  

### Infrastructure Layer
- Spring MVC controllers  
- JPA repositories  
- Database configuration  
- Thymeleaf templates (UI)  


---

## 5. How to Run the Application

The system runs using **Docker** for both backend and the MySQL database.

### Build the application image

docker compose build uyp-pms

### Start the application

docker compose up --build

### Access

Application: **http://localhost:8080**  
MySQL database: **localhost:6033 → 3306**

---

### 6. Initial Login Credentials

Only one account exists initially:

#### Administrator
- **Email:** admin@uypms.com  
- **Password:** admin123  

The administrator can then create:

- Pharmacists (pharm-001, pharm-002, …)  
- Assistants (assist-001, assist-002, …)  

---

### 7. Conclusion

UYP-PMS implements a complete, role-based prescription management system aligned with the project requirements.  
The architecture respects **DDD principles**, with a clean separation of concerns.  
