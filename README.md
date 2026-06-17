# WebApp - Secure Spring Boot Web Application

A Spring Boot web application with integrated security features, built with Spring Security, Thymeleaf templating engine, and SQLite-backed user persistence.

## Project Overview

This project demonstrates a secure web application using Spring Boot with the following features:
- **Spring Security**: Form-based authentication and authorization
- **Thymeleaf**: Server-side template rendering
- **SQLite Database**: Local persistent storage for user credentials
- **User Registration**: Self-service account creation with duplicate-username prevention
- **BCrypt Password Hashing**: Secure password storage
- **MVC Architecture**: Clean separation of concerns with Spring MVC

## Technology Stack

- **Java Version**: 25
- **Spring Boot**: 4.1.0
- **Spring Security**: Latest (via Spring Boot)
- **Spring Data JPA**: Database access via Hibernate ORM
- **SQLite**: Lightweight local database (`webapp.db`)
- **Hibernate Community Dialects**: SQLiteDialect for Hibernate 7.x
- **Thymeleaf**: Latest with Spring Security extras
- **Build Tool**: Gradle
- **Testing**: JUnit Platform

## Project Structure

```
WebApp/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/secure/WebApp/
│   │   │       ├── WebAppApplication.java          # Main Spring Boot application entry point
│   │   │       ├── WebSecurityConfig.java          # Security configuration
│   │   │       ├── MvcConfig.java                  # MVC view controller configuration
│   │   │       ├── controller/
│   │   │       │   └── RegistrationController.java # User registration endpoints
│   │   │       ├── model/
│   │   │       │   └── User.java                   # JPA entity for users table
│   │   │       ├── repository/
│   │   │       │   └── UserRepository.java         # Spring Data JPA repository
│   │   │       └── service/
│   │   │           ├── CustomUserDetailsService.java # Spring Security UserDetailsService impl
│   │   │           └── UserRegistrationService.java  # Registration business logic
│   │   └── resources/
│   │       ├── application.properties              # Application & database configuration
│   │       └── templates/
│   │           ├── home.html                       # Home page
│   │           ├── hello.html                      # Authenticated greeting page
│   │           ├── login.html                      # Login page
│   │           └── register.html                   # Registration page
│   └── test/
│       └── java/
│           └── com/secure/WebApp/
│               └── WebAppApplicationTests.java     # Application tests
├── gradle/
│   └── wrapper/                                    # Gradle wrapper configuration
├── build.gradle                                    # Gradle build configuration
├── settings.gradle                                 # Gradle settings
├── .gitignore                                      # Git ignore rules (includes *.db)
└── README.md                                       # This file
```

## Getting Started

### Prerequisites

- Java 25 or higher
- Gradle (or use the included Gradle wrapper)

### Building the Application

```bash
# Using Gradle wrapper
./gradlew build

# Or using Gradle directly
gradle build
```

### Running the Application

```bash
# Using Gradle wrapper
./gradlew bootRun

# Or using Gradle directly
gradle bootRun
```

The application will start on `http://localhost:9000`.

## Configuration

### Application Properties

- **Application Name**: WebApp
- **Server Port**: 9000
- **Database**: SQLite (`webapp.db` created in project root at runtime)
- **Schema Management**: `ddl-auto=update` (auto-creates/updates tables)
- Configuration file: `src/main/resources/application.properties`

### Database

The application uses a local SQLite database file (`webapp.db`) that is automatically created on first run. The `users` table schema:

| Column   | Type    | Constraints        |
|----------|---------|--------------------|
| id       | INTEGER | Primary key, auto-increment |
| username | TEXT    | Unique, not null   |
| password | TEXT    | Not null (BCrypt hash) |
| role     | TEXT    | Not null (default: `USER`) |

The database file is excluded from version control via `.gitignore`.

### Security Configuration

The application includes the following security features:

#### Authentication
- **Type**: Database-backed authentication via SQLite
- **Password Encoding**: BCryptPasswordEncoder
- **User Registration**: Self-service via `/register`

#### Authorization Rules
- **Public Routes** (no authentication required):
  - `/` (root path)
  - `/home` (home page)
  - `/register` (registration page)
- **Protected Routes** (authentication required):
  - `/hello`
  - Any other routes not explicitly permitted
- **Login Page**: `/login` (accessible without authentication)

#### Security Features
- Form-based login
- Logout functionality with logout confirmation
- Spring Security Thymeleaf extras for secure template rendering
- Client-side password confirmation on registration form

## Available Routes

| Route       | View     | Description              | Authentication |
|-------------|----------|--------------------------|----------------|
| `/`         | home     | Root/home page           | Public         |
| `/home`     | home     | Home page                | Public         |
| `/hello`    | hello    | Authenticated greeting   | Protected      |
| `/login`    | login    | Login page               | Public         |
| `/register` | register | User registration page   | Public         |

## Dependencies

### Core Dependencies

```gradle
- org.springframework.boot:spring-boot-starter-web
- org.springframework.boot:spring-boot-starter-security
- org.springframework.boot:spring-boot-starter-thymeleaf
- org.thymeleaf.extras:thymeleaf-extras-springsecurity6
- org.springframework.boot:spring-boot-starter-data-jpa
- org.xerial:sqlite-jdbc:3.49.1.0
- org.hibernate.orm:hibernate-community-dialects:7.0.0.Final
```

### Test Dependencies

```gradle
- org.springframework.boot:spring-boot-starter-security-test
- org.springframework.boot:spring-boot-starter-thymeleaf-test
- org.junit.platform:junit-platform-launcher
```

## Testing

Run the test suite:

```bash
./gradlew test
```

Tests are configured to use JUnit Platform.

## Development

### Main Application Class

- **Location**: `src/main/java/com/secure/WebApp/WebAppApplication.java`
- **Purpose**: Entry point for the Spring Boot application

### Security Configuration

- **Location**: `src/main/java/com/secure/WebApp/WebSecurityConfig.java`
- **Purpose**: Defines security policies, authorization rules, and password encoding

### Registration Flow

1. User navigates to `/register`
2. Fills in username, password, and password confirmation
3. Client-side validation checks password match
4. Server-side validation checks for empty fields and duplicate usernames
5. Password is BCrypt-encoded and user is persisted to SQLite
6. User is redirected to `/login?registered` with a success message

### MVC Configuration

- **Location**: `src/main/java/com/secure/WebApp/MvcConfig.java`
- **Purpose**: Registers view controllers for simple template mappings (`/home`, `/`, `/hello`, `/login`)

## Project Metadata

- **Group ID**: com.secure
- **Artifact ID**: WebApp
- **Version**: 0.0.1-SNAPSHOT
