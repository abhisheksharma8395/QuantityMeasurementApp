# Quantity Measurement Application

A backend application built using Spring Boot that performs quantity measurement operations such as comparison, conversion, and arithmetic across multiple measurement categories.

This project evolved from basic object-oriented design into a layered backend system with database persistence and secure authentication using JWT.

---

## Overview

The application supports:

* Quantity comparison
* Unit conversion
* Arithmetic operations (add, subtract, divide)
* Measurement history storage
* REST API architecture
* MySQL database integration
* JWT-based authentication
* BCrypt password encryption

---

## 🔐 Authentication & Security

The system implements secure authentication using:

* JWT (JSON Web Token)
* Spring Security
* Stateless session management
* BCrypt password hashing
* Database-based login validation

### Authentication Flow

1. User registers → password stored as hashed value
2. User logs in → credentials verified from database
3. JWT token is generated
4. Token is sent in Authorization header
5. JwtFilter validates token for protected APIs

---

## Tech Stack

* Java 17
* Spring Boot
* Spring Web
* Spring Data JPA
* Spring Security
* JWT (jjwt)
* BCrypt Password Encoder
* MySQL Database
* JUnit 5

---

## Supported Measurement Categories

### Length

* FEET
* INCHES
* YARD
* CENTIMETER

### Weight

* KILOGRAM
* GRAM
* TONNE

### Volume

* LITER
* MILLILITER
* GALLON

### Temperature

* CELSIUS
* FAHRENHEIT
* KELVIN

---

## Project Structure

```text
com.apps.quantitymeasurement
│
├── controller
├── service
├── repository
├── entity
├── dto
├── security
├── util
└── QuantityMeasurementApplication
```

---

## Authentication APIs

### Register

```http
POST /auth/register
```

```json
{
  "username": "abhi",
  "password": "123"
}
```

---

### Login

```http
POST /auth/login
```

```json
{
  "username": "abhi",
  "password": "123"
}
```

Response:

```text
JWT_TOKEN
```

---

### Use Token

```text
Authorization: Bearer <JWT_TOKEN>
```

---

## Available APIs

Base path:

```text
/api/quantity
```

### POST APIs

* `/compare`
* `/convert`
* `/add`
* `/subtract`
* `/divide`

---

## Sample Requests

### Compare

```json
{
  "q1": {
    "value": 1,
    "unit": "FEET",
    "measurementType": "LENGTH"
  },
  "q2": {
    "value": 12,
    "unit": "INCHES",
    "measurementType": "LENGTH"
  }
}
```

---

### Convert

```json
{
  "q1": {
    "value": 1,
    "unit": "FEET",
    "measurementType": "LENGTH"
  },
  "q2": {
    "value": 1,
    "unit": "INCHES",
    "measurementType": "LENGTH"
  }
}
```

---

### Add (with target unit)

```json
{
  "q1": {
    "value": 2,
    "unit": "FEET",
    "measurementType": "LENGTH"
  },
  "q2": {
    "value": 12,
    "unit": "INCHES",
    "measurementType": "LENGTH"
  },
  "targetUnit": {
    "value": 1,
    "unit": "YARDS",
    "measurementType": "LENGTH"
  }
}
```

---

## Running the Application

```bash
mvn spring-boot:run
```

Application runs at:

```text
http://localhost:8080
```

---

## Testing

```bash
mvn test
```

---

## Key Design Principles

* Layered Architecture (Controller → Service → Repository)
* Separation of Concerns
* DTO-based communication
* Stateless authentication using JWT
* Secure password handling using BCrypt

---

## Key Highlights

* REST API development using Spring Boot
* MySQL database integration using JPA
* JWT-based authentication system
* BCrypt password encryption
* Custom JWT filter for request validation
* Scalable and modular architecture

---
