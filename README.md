# WebApp - Secure Spring Boot Web Application

A Spring Boot web application with integrated security features, built with Spring Security and Thymeleaf templating engine.

## Project Overview

This project demonstrates a secure web application using Spring Boot with the following features:
- **Spring Security**: Form-based authentication and authorization
- **Thymeleaf**: Server-side template rendering
- **In-Memory Authentication**: Built-in user credentials for development
- **MVC Architecture**: Clean separation of concerns with Spring MVC

## Technology Stack

- **Java Version**: 25
- **Spring Boot**: 4.1.0
- **Spring Security**: Latest (via Spring Boot)
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
│   │   │       ├── WebAppApplication.java      # Main Spring Boot application entry point
│   │   │       ├── WebSecurityConfig.java      # Security configuration and authentication
│   │   │       └── MvcConfig.java              # MVC view controller configuration
│   │   └── resources/
│   │       ├── application.properties          # Application configuration
│   │       └── templates/
│   │           ├── home.html                   # Home page
│   │           ├── hello.html                  # Hello page
│   │           └── login.html                  # Login page
│   └── test/
│       └── java/
│           └── com/secure/WebApp/
│               └── WebAppApplicationTests.java # Application tests
├── gradle/
│   └── wrapper/                                # Gradle wrapper configuration
├── build.gradle                                # Gradle build configuration
├── settings.gradle                             # Gradle settings
└── README.md                                   # This file
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

The application will start on `http://localhost:8080` by default.

## Configuration

### Application Properties

- **Application Name**: WebApp
- Configuration file: `src/main/resources/application.properties`

### Security Configuration

The application includes the following security features:

#### Authentication
- **Type**: In-Memory Authentication (development configuration)
- **Default User Credentials**:
  - Username: `user`
  - Password: `password`
- **Password Encoding**: BCryptPasswordEncoder

#### Authorization Rules
- **Public Routes** (no authentication required):
  - `/` (root path)
  - `/home` (home page)
- **Protected Routes** (authentication required):
  - `/hello`
  - Any other routes not explicitly permitted
- **Login Page**: `/login` (accessible without authentication)

#### Security Features
- Form-based login
- Logout functionality with logout confirmation
- Spring Security Thymeleaf extras for secure template rendering

## Available Routes

The application provides the following view controllers:

| Route | View | Description | Authentication |
|-------|------|-------------|-----------------|
| `/` | home | Root/home page | Public |
| `/home` | home | Home page | Public |
| `/hello` | hello | Hello page | Protected |
| `/login` | login | Login page | Public |

## Dependencies

### Core Dependencies

```gradle
- org.springframework.boot:spring-boot-starter-security
- org.springframework.boot:spring-boot-starter-thymeleaf
- org.thymeleaf.extras:thymeleaf-extras-springsecurity6
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
- **Purpose**: Defines security policies, authentication, and password encoding

### MVC Configuration

- **Location**: `src/main/java/com/secure/WebApp/MvcConfig.java`
- **Purpose**: Registers view controllers for template mapping

## Default Login Credentials (Development Only)

For local development and testing, the following credentials are available:

```
Username: user
Password: password
```

**Note**: These are hardcoded in-memory credentials and should be replaced with a proper authentication mechanism (e.g., database-backed authentication) for production use.

## Project Metadata

- **Group ID**: com.secure
- **Artifact ID**: WebApp
- **Version**: 0.0.1-SNAPSHOT
