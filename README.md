# 💳 Fintech Digital Banking Platform

A full-stack **Digital Banking Application** built using **Spring Boot, React, and MySQL**.
This project simulates real banking features such as authentication, account balance management, money transfers, and transaction history.

---

# 🚀 Features

✔ User Authentication (JWT Security)
✔ Secure Login System
✔ Account Balance Dashboard
✔ Transfer Money Between Accounts
✔ Transaction History Tracking
✔ Protected Routes in React
✔ RESTful APIs using Spring Boot
✔ MySQL Database Integration

---

# 🛠 Tech Stack

### Backend

* Java 17
* Spring Boot
* Spring Security
* Spring Data JPA
* MySQL
* Maven

### Frontend

* React.js
* Axios
* React Router
* CSS

### Tools

* Swagger API Documentation
* Postman
* Git & GitHub

---

# 📂 Project Structure

```
Fintech Digital Banking Platform
│
├── banking-frontend
│   ├── node_modules
│   ├── public
│   ├── src
│   ├── package.json
│   └── package-lock.json
│
├── Spring boot-project
│   └── banking-platform
│       ├── src
│       ├── pom.xml
│       └── target
│
├── screenshots
│   ├── dashboard.png
│   ├── login1.png
│   ├── login2.png
│   ├── transfer.png
│   └── transactions.png
│
├── README.md
└── .gitignore
```

---

# ⚙️ Backend Setup

Navigate to backend folder

```
cd Spring boot-project/banking-platform
```

Configure database in **application.properties**

```
spring.datasource.url=jdbc:mysql://localhost:3306/fintech_bank
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

server.port=8081
```

Run Spring Boot application

```
mvn spring-boot:run
```

Backend will run at:

```
http://localhost:8081
```

---

# ⚙️ Frontend Setup

Navigate to frontend folder

```
cd banking-frontend
```

Install dependencies

```
npm install
```

Run React application

```
npm start
```

Frontend runs at:

```
http://localhost:3000
```

---

# 🔑 API Endpoints

### Authentication

```
POST /api/auth/login
POST /api/auth/register
```

### Account Operations

```
POST /api/accounts/deposit
POST /api/accounts/withdraw
POST /api/accounts/transfer
GET /api/accounts/user/{userId}
```

### Transactions

```
GET /api/accounts/{accountId}/transactions
```

---

# 🖼 Screenshots

### Login Page

![Login](screenshots/login1.png)

### Dashboard

![Dashboard](screenshots/dashboard.png)

### Transfer Money

![Transfer](screenshots/transfer.png)

### Transaction History

![Transactions](screenshots/transactions.png)

---

# 🔒 Security

* JWT Authentication
* Protected Backend APIs
* Secure Login
* React Protected Routes

---

# 📈 Future Improvements

* Email Notifications
* Admin Dashboard
* Account Statements
* Docker Deployment
* Cloud Deployment

---

# 👩‍💻 Author

**Venkata Navya Sri Nunna**

Aspiring Software Engineer passionate about building scalable full-stack applications.

### Skills

* Java
* Spring Boot
* React.js
* MySQL
* REST APIs
* JWT Authentication

---

⭐ If you like this project, give it a **Star on GitHub**.
