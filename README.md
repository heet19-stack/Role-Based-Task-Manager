# 🚀 Role-Based Task Manager

A production-inspired Role-Based Task Manager REST API built using **Spring Boot**, **Spring Security**, **JWT Authentication**, and **MySQL**. The application provides secure authentication, role-based authorization, employee task management, and admin controls through RESTful APIs.

---

## 📌 Project Overview

The application supports two user roles:

- **Admin**
- **Employee**

Employees can manage their own tasks, while administrators can manage employees, assign tasks, approve signup requests, monitor dashboard statistics, and control employee accounts.

Authentication is implemented using **JWT Access Tokens** and **Refresh Tokens**, ensuring secure and stateless communication.

---

# ✨ Features

## 🔐 Authentication

- User Signup
- User Login
- JWT Access Token
- Refresh Token
- Signup Approval Workflow
- Role-Based Authorization
- Secure Password Encryption (BCrypt)

---

## 👨‍💼 Employee Features

- Create Task
- View All Assigned Tasks
- View Single Task
- Update Task
- Delete Task
- Update Task Status
- Search Tasks
- Filter Tasks by Status

---

## 👨‍💻 Admin Features

- View All Employees
- Search Employees
- View Employee Details
- View Employee Tasks
- View Completed Tasks
- View Pending Tasks
- Assign Task
- Assign Bulk Tasks
- Update Employee Task
- Delete Employee Task
- Enable Employee
- Disable Employee
- Dashboard Statistics
- Recent Tasks

---

# 🛠️ Tech Stack

### Backend

- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- Maven

### Database

- MySQL

### Authentication

- JWT (JSON Web Token)
- Refresh Tokens
- BCrypt Password Encoder

### API Testing

- Postman
- Postman Environment Variables
- Postman Automation Scripts

---

# 📂 Project Structure

```
src
├── controller
├── service
├── repository
├── entity
├── dto
├── security
├── config
├── exception
└── util
```

---

# 🔑 Authentication Flow

```
Signup
      │
      ▼
Admin Approval
      │
      ▼
Login
      │
      ▼
JWT Access Token
      │
      ▼
Protected APIs
```

---

# 📖 REST API Modules

### Authentication

- Signup
- Login
- Refresh Token
- Approve Signup Request
- Reject Signup Request
- Get Signup Requests

### Employee APIs

- Create Task
- Get Tasks
- Get Single Task
- Update Task
- Delete Task
- Update Task Status
- Search Tasks
- Filter Tasks

### Admin APIs

- Dashboard Statistics
- Get Employees
- Search Employees
- Get Employee Details
- Get Employee Tasks
- Get Single Employee Task
- Assign Task
- Bulk Assign Tasks
- Update Task
- Delete Task
- Enable Employee
- Disable Employee
- Recent Tasks

---

# 🧪 API Testing

A complete Postman Collection is included for testing all APIs.

Features include:

- Collection-Level Authorization
- Environment Variables
- Automatic JWT Storage
- Automatic User ID Storage
- Automatic Task ID Storage

---

# 🗄️ Database

The application uses **MySQL** as the relational database.

---

# 🔒 Security

- JWT Authentication
- Stateless Authentication
- Spring Security
- Method-Level Authorization
- Role-Based Access Control
- BCrypt Password Encoding

---

# 📈 Future Enhancements

- React Frontend
- Docker
- Docker Compose
- Shell Scripts
- GitHub Actions CI/CD
- AWS EC2 Deployment
- AWS RDS Integration
- IAM Role Configuration

---

# 👨‍💻 Author

**Heet Shah**

BSc Information Technology Student | Java Backend Developer

---

## 🚀 Project Roadmap

- [x] Spring Boot REST API
- [x] JWT Authentication
- [x] Refresh Tokens
- [x] Role-Based Authorization
- [x] Postman Collection
- [ ] React Frontend
- [ ] Docker
- [ ] Docker Compose
- [ ] Shell Deployment Script
- [ ] GitHub Actions (CI/CD)
- [ ] AWS EC2 Deployment
- [ ] AWS RDS Integration

## ⭐ If you found this project helpful, consider giving it a star!