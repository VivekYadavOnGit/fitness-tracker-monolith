# 🏋️ Fitness Tracker Backend

A secure and scalable Fitness Tracker Backend application built using Java, Spring Boot, Spring Security, JWT Authentication, Hibernate/JPA, and PostgreSQL. The system allows users to manage workouts, track fitness progress, and securely access their fitness data through RESTful APIs.

---

## 🚀 Features

### Authentication & Security

* User Registration
* User Login
* JWT-based Authentication
* Password Encryption using BCrypt
* Role-Based Access Control
* Secure REST APIs

### Workout Management

* Create Workout Plans
* View Workouts
* Update Workouts
* Delete Workouts
* Exercise Tracking

### Progress Tracking

* Record Fitness Progress
* Track Workout History
* Monitor Performance Metrics
* Store Personal Fitness Data

### System Features

* RESTful API Architecture
* Global Exception Handling
* Request Validation
* Layered Architecture
* Docker Support
* Database Integration using JPA/Hibernate

---

## 🛠️ Tech Stack

### Backend

* Java 21
* Spring Boot 3
* Spring Security
* Spring Data JPA
* Hibernate

### Database

* PostgreSQL

### Authentication

* JWT (JSON Web Token)

### Build Tools

* Maven

### DevOps

* Docker
* Docker Compose

### Version Control

* Git
* GitHub

---

## 📂 Project Structure

```text
fitness-tracker-backend
│
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── controller
│   │   │   ├── service
│   │   │   ├── repository
│   │   │   ├── entity
│   │   │   ├── dto
│   │   │   ├── security
│   │   │   └── exception
│   │   │
│   │   └── resources
│   │       └── application.properties
│   │
│   └── test
│
├── Dockerfile
├── pom.xml
└── README.md
```

## ⚙️ Installation & Setup

### Clone Repository

```bash
git clone https://github.com/VivekYadavOnGit/fitness-tracker-monolith.git

cd fitness-tracker-monolith
```

### Configure Database

Update application.properties:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/fitness_tracker

spring.datasource.username=postgres

spring.datasource.password=password
```

### Build Project

```bash
mvn clean install
```

### Run Application

```bash
mvn spring-boot:run
```

Application will start at:

```text
http://localhost:8080
```

---

## 🔐 Authentication Flow

1. Register User
2. Login User
3. Receive JWT Token
4. Attach Token in Authorization Header

```http
Authorization: Bearer <JWT_TOKEN>
```

5. Access Protected Endpoints

---

## 📌 Sample API Endpoints

### Authentication

```http
POST /api/auth/register
```

```http
POST /api/auth/login
```

### Workouts

```http
GET /api/workouts
```

```http
POST /api/workouts
```

```http
PUT /api/workouts/{id}
```

```http
DELETE /api/workouts/{id}
```

### Progress

```http
GET /api/progress
```

```http
POST /api/progress
```

---

## 🐳 Docker Support

Build Docker Image

```bash
docker build -t fitness-tracker .
```

Run Container

```bash
docker run -p 8080:8080 fitness-tracker
```

---

## 📈 Future Enhancements

* Workout Analytics Dashboard
* Calorie Tracking
* Goal Management
* Email Notifications
* AI-Based Fitness Recommendations
* Mobile Application Integration
* Microservices Architecture

---

## 🧪 Testing

Run Tests:

```bash
mvn test
```

---

## 👨‍💻 Author

**Vivek Yadav**

Aspiring Java Full Stack Developer

Skills:
Java • Spring Boot • Spring Security • Hibernate • PostgreSQL • JWT • Docker • React • REST APIs

---

## ⭐ Support

If you found this project helpful, please consider giving it a star on GitHub.

```text
Star ⭐ the repository if you like it!
```
