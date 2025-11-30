# Auth Service

A robust, containerized Authentication Service built with Java 21 and Spring Boot 3.5.0. This service implements **Hexagonal Architecture** (Ports and Adapters) to ensure separation of concerns and maintainability. It handles user registration, login, role-based access control, and JWT token management.

## 🚀 Technologies & Stack

* **Language:** Java 21
* **Framework:** Spring Boot 3.5.0
* **Database:** PostgreSQL 15.2
* **Security:** Spring Security & JWT (JSON Web Tokens)
* **Migration:** Flyway
* **Build Tool:** Maven
* **Containerization:** Docker & Docker Compose
* **CI/CD:** GitHub Actions

## 🏗 Architecture

The project follows a **Hexagonal Architecture** structure:

* **Domain:** Core business logic and entities (`domain.model`, `domain.validation`).
* **Application:** Use cases and ports (`application.port`, `application.service`).
* **Adapter:** Interactions with the outside world.
    * **In:** Web controllers (`adapter.in.web`).
    * **Out:** Database persistence (`adapter.out.persistence`).

## ⚙️ Features

* **User Management:** Register, Update, Delete, and Retrieve users.
* **Authentication:** * Standard User Login
    * Admin Login
    * JWT Token Generation & Validation
* **Authorization:** Role-Based Access Control (RBAC) with predefined roles:
    * `SUPERUSER`
    * `ADMIN`
    * `USER`
* **Automatic Admin Initialization:** Automatically creates a superuser/admin on startup if configured.
* **Validation:** Strict input validation using Jakarta Validation.
* **Monitoring:** Spring Boot Actuator with Prometheus metrics enabled.

## 🛠️ Configuration & Environment Variables

The application relies on environment variables for configuration (likely stored in a `.env` file for Docker).

| Variable | Description |
| :--- | :--- |
| `SERVER_PORT` | Port the service runs on (e.g., `8088`) |
| `POSTGRES_HOST` | Database host (use `auth-db` for Docker Compose) |
| `POSTGRES_PORT` | Database port (default `5432`) |
| `POSTGRES_DB` | Database name |
| `POSTGRES_USER` | Database username |
| `POSTGRES_PASSWORD` | Database password |
| `JWT_SECRET` | Secret key for signing JWT tokens |
| `JWT_EXPIRATION` | Token expiration time in milliseconds |
| `APP_USERNAME` | Username for the initial Admin account |
| `APP_EMAIL` | Email for the initial Admin account |
| `APP_PASSWORD` | Password for the initial Admin account |

## 🚀 Getting Started

### Prerequisites
* Docker & Docker Compose
* Java 21 (if running locally without Docker)
* Maven

### Running with Docker Compose (Recommended)

1.  **Create a `.env` file** in the root directory with the variables listed above.
2.  **Build and Run:**
    ```bash
    docker-compose up --build
    ```
    This will start the PostgreSQL container (`auth-db`) and the application container (`auth-service`).

### Running Locally with Maven

1.  Ensure a PostgreSQL database is running and accessible.
2.  Update `src/main/resources/application.properties` or set environment variables.
3.  **Run the application:**
    ```bash
    ./mvnw spring-boot:run
    ```

## 🔌 API Endpoints

### Public Endpoints
* `POST /auth/register` - Register a new user.
* `POST /auth/login` - Login for standard users.
* `POST /auth/admin/login` - Login for administrators.

### Protected Endpoints (Requires `Bearer Token`)
* `GET /auth` - Get all users (**Admin only**).
* `GET /auth/{userId}` - Get specific user details (**Admin only**).
* `PUT /auth` - Update user details (**Admin only**).
* `DELETE /auth/{userId}` - Delete a user (**Admin only**).

## 🧪 Testing

Run the unit and integration tests using Maven:

```bash
./mvnw test
