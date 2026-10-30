# E-Commerce Backend System

A RESTful backend application for an e-commerce system, developed using **Java Spring Boot** and **MongoDB**. The application manages products, categories, orders, and payment methods through REST API endpoints.

## Technologies Used

- **Java 21**
- **Spring Boot 4**
- **Spring Data MongoDB**
- **MongoDB**
- **Maven**
- **IntelliJ IDEA**
- **Postman** – API testing

## Project Structure

```text
ecommerce-backend/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── Practice/
│       │       ├── Application.java
│       │       ├── Controller/
│       │       ├── Model/
│       │       ├── Repository/
│       │       └── Service/
│       │
│       └── resources/
│           └── application.properties
│
├── pom.xml
└── README.md
```

## Main Features

- Product management
- Category management
- Order management
- Payment method management
- RESTful API development
- MongoDB database integration
- CRUD operations through API endpoints

## Architecture

The project follows a layered architecture:

| Layer | Responsibility |
|---|---|
| Controller | Handles incoming HTTP requests |
| Service | Contains business logic |
| Repository | Communicates with MongoDB |
| Model | Defines the data structures |

## API Endpoints

Base URL: `http://localhost:8080`

| Resource | Endpoint |
|---|---|
| Products | `/api/products` |
| Categories | `/api/categories` |
| Orders | `/api/orders` |
| Payment Methods | `/api/payments` |

The endpoints support the operations implemented in their respective controllers.

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/ManimegalaiVijayarasa/ecommerce-backend.git
```

### 2. Open the Project

Open the cloned project using IntelliJ IDEA.

### 3. Configure MongoDB

Make sure MongoDB is installed and running locally.

Configure the database connection in:

`src/main/resources/application.properties`

Example:

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/ecommerce
```

### 4. Run the Application

Run `Application.java` from IntelliJ IDEA.

The application will start at:

`http://localhost:8080`

### 5. Test the APIs

Use Postman to send HTTP requests to the available API endpoints.

## Author

**Manimegalai Vijayarasa**

GitHub: [ManimegalaiVijayarasa](https://github.com/ManimegalaiVijayarasa)

---

*This project was developed as a practical backend development project to gain experience in Java, Spring Boot, REST APIs, and MongoDB integration.*