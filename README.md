# Document Management System

> Application build with Spring Boot on the backend and React on the frontend, with 
Maven as build tool.
### Technology Stack
Component         | Technology
---               | ---
Frontend          | React 16+
Backend           | Spring Boot 2.6.7, Java 8, Hibernate 5
Security          | Spring Security, JWT
Database          | PostgreSQL Database
Persistence       | JPA 
Client Build      | npm
Server Build      | Maven

## Getting Started

### Prerequisites
-  Install Java 8 from the OpenJDK
-  Install Node.js from the Node.js website
-  Clone the repo `git clone https://github.com/haririalii/DocumentManagementSystem.git`

### Backend
- Go to your project folder from your terminal
- cd dms
- enter your database information at `src/main/resources/application.properties`
- Run: `mvnw spring-boot:run`
- Run SQL script in SQL folder to populate tables with necessary data

### Frontend
- Go to your project folder from your terminal
- cd React
- Run: `npm install`
- After install, run: `npm start` 
- It will open your browser(http://localhost:3000)

