# User Management API

This is a Spring Boot-based User Management API that allows users to register, update their details, change their passwords, and delete their accounts.

## Technologies Used

- Java
- Spring Boot
- Spring Web
- REST API
- JSON
- PostgreSQL

## Project Structure
```
User Management API
├── src/main/java/com/aniket/Login
│   ├── Controller
│   │   └── UserController.java        # Manages user-related HTTP requests
│   ├── Service
│   │   ├── UserService.java           # Implements business logic for user operations
│   │   ├── MyUserDetailService.java   # Handles user authentication and authorization details
│   ├── Model
│   │   ├── Users.java                 # Represents the User entity
│   │   ├── UsersWrapper.java          # Wraps user details
│   │   ├── PasswordWrapper.java       # Handles password update requests
│   │   ├── UserPrincipal.java         # Implements UserDetails for Spring Security
│   ├── Repository
│   │   └── UserRepository.java        # Interface for database operations
│   ├── Config
│   │   └── SecurityConfig.java        # Configures Spring Security settings
│   ├── Application.java               # Main class to run the Spring Boot application
├── src/main/resources
│   └── application.properties         # Contains database and application configurations
```


## Endpoints

### 1. User Registration

**Endpoint:** `POST /register`

**Description:** Registers a new user. This endpoint does not require authentication.

**Request Body:**

```json
{
  "username": "aniket",
  "email": "aniketbaravkar@gmail.com",
  "password": "password"
}
```

**Response:**

- Success or failure message.

---

### 2. Delete User (Requires Basic Auth)

**Endpoint:** `GET /delete`

**Description:** Deletes the currently logged-in user.

**Authentication:** Basic Authentication is required.

**Response:**

- Success or failure message.

---

### 3. Edit User Details (Requires Basic Auth)

**Endpoint:** `POST /edit`

**Description:** Updates user details such as username or email.

**Authentication:** Basic Authentication is required.

**Request Body:**

```json
{
  "username": "newUsername",
  "email": "newEmail@example.com"
}
```

**Response:**

- Success or failure message.

---

### 4. Get User Details (Requires Basic Auth)

**Endpoint:** `GET /userDetails`

**Description:** Retrieves the details of the currently logged-in user.

**Authentication:** Basic Authentication is required.

**Response:**

```json
{
  "username": "currentUsername",
  "email": "currentEmail@example.com"
}
```

---

### 5. Edit Password (Requires Basic Auth)

**Endpoint:** `POST /editPassword`

**Description:** Updates the user's password.

**Authentication:** Basic Authentication is required.

**Request Body:**

```json
{
  "currentPassword": "oldPassword",
  "newPassword": "newPassword"
}
```

**Response:**

- Success or failure message.

## Setup Instructions

1. Clone the repository:
   ```sh
   git clone https://github.com/your-repo.git
   ```
2. Navigate to the project directory:
   ```sh
   cd your-project-folder
   ```
3. Configure the application properties:
   - `Note :` User must have postgres to run this project
   - Navigate to `src/main/resources/application.properties`.
   - Update database configuration and other necessary settings:
   ```properties
   # Database Configuration
   ## PostgreSQL Database URL (Replace 'your_database' with the actual database name)
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
   
   ## PostgreSQL Database/Server Username (Replace 'postgres' with your actual username)
   spring.datasource.username=postgres
   
   ## PostgreSQL Database/Server Password (Replace '123456' with your actual password)
   spring.datasource.password=123456
   
   spring.datasource.driver-class-name=org.postgresql.Driver
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
   ```
4. Run the application:
   ```sh
   mvn spring-boot:run
   ```


