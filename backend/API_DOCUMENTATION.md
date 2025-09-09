# Spring Boot 3.0 REST API Documentation

## Overview
This is a comprehensive Spring Boot 3.0 REST API with user and role management, JWT authentication, and role-based access control.

## Features
- User Management (CRUD operations)
- Role Management
- User-Role Assignment
- JWT Authentication
- Role-based Authorization
- Input Validation
- Global Exception Handling
- CORS Support

## Database Schema
The API uses the following database tables:
- `users` - Stores user information
- `roles` - Stores role definitions
- `user_roles` - Many-to-many relationship between users and roles

## API Endpoints

### Authentication
- `POST /api/auth/login` - User login (returns JWT token)

### User Management
- `POST /api/users` - Create user (ADMIN only)
- `GET /api/users/{id}` - Get user by ID
- `GET /api/users/username/{username}` - Get user by username
- `GET /api/users/uuid/{uuid}` - Get user by UUID
- `GET /api/users` - Get all users (ADMIN only)
- `GET /api/users/active` - Get active users (ADMIN only)
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user (soft delete, ADMIN only)
- `POST /api/users/{userId}/roles/{roleId}` - Assign role to user (ADMIN only)
- `DELETE /api/users/{userId}/roles/{roleId}` - Remove role from user (ADMIN only)
- `GET /api/users/{userId}/roles` - Get user roles

### Role Management
- `POST /api/roles` - Create role (ADMIN only)
- `GET /api/roles/{id}` - Get role by ID (ADMIN only)
- `GET /api/roles/name/{name}` - Get role by name (ADMIN only)
- `GET /api/roles` - Get all roles (public)
- `DELETE /api/roles/{id}` - Delete role (ADMIN only)

## Request/Response Examples

### Login Request
```json
POST /api/auth/login
{
  "username": "admin",
  "password": "password"
}
```

### Login Response
```json
{
  "success": true,
  "message": "Login successful",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "type": "Bearer",
    "user": {
      "id": 1,
      "uuid": "123e4567-e89b-12d3-a456-426614174000",
      "username": "admin",
      "email": "admin@example.com",
      "displayName": "Administrator",
      "isActive": true,
      "createdAt": "2024-01-01T00:00:00",
      "updatedAt": "2024-01-01T00:00:00",
      "roles": [
        {
          "id": 1,
          "name": "ADMIN"
        }
      ]
    },
    "roles": [
      {
        "id": 1,
        "name": "ADMIN"
      }
    ]
  },
  "timestamp": "2024-01-01T00:00:00"
}
```

### Create User Request
```json
POST /api/users
{
  "username": "john_doe",
  "email": "john@example.com",
  "displayName": "John Doe"
}
```

### Create Role Request
```json
POST /api/roles
{
  "id": 4,
  "name": "EDITOR"
}
```

## Security Configuration

### Default Roles
The system initializes with the following default roles:
- ADMIN (ID: 1)
- USER (ID: 2)
- MANAGER (ID: 3)

### Authorization Rules
- Authentication endpoints are public
- Role listing is public
- User management requires USER or ADMIN role
- Admin operations require ADMIN role
- All endpoints require valid JWT token (except public ones)

### JWT Token Usage
Include the JWT token in the Authorization header:
```
Authorization: Bearer <your-jwt-token>
```

## Configuration

### Application Properties
```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/cab_db
spring.datasource.username=root
spring.datasource.password=your_password

# JWT Configuration
jwt.secret=mySecretKey
jwt.expiration=86400000

# CORS Configuration
cors.allowed-origins=http://localhost:4200
cors.allowed-methods=GET,POST,PUT,DELETE,OPTIONS
cors.allowed-headers=*
cors.allow-credentials=true
```

## Error Handling
The API returns consistent error responses:
```json
{
  "success": false,
  "message": "Error description",
  "data": null,
  "timestamp": "2024-01-01T00:00:00"
}
```

### Common HTTP Status Codes
- 200 OK - Success
- 201 Created - Resource created successfully
- 400 Bad Request - Validation errors
- 401 Unauthorized - Authentication required
- 403 Forbidden - Insufficient permissions
- 404 Not Found - Resource not found
- 409 Conflict - Resource already exists
- 500 Internal Server Error - Server error

## Getting Started

1. **Database Setup**
   - Create MySQL database named `cab_db`
   - Update database credentials in `application.properties`

2. **Build and Run**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

3. **API Testing**
   - The API will be available at `http://localhost:8080`
   - Use tools like Postman or curl to test endpoints
   - Default roles will be created automatically on startup

## Dependencies
- Spring Boot 3.5.5
- Spring Security
- Spring Data JPA
- MySQL Connector
- JWT (jjwt)
- Validation API
- Lombok (optional)

## Notes
- All timestamps are in ISO 8601 format
- User deletion is soft delete (sets isActive to false)
- UUIDs are automatically generated for users
- Password handling is simplified for this example (in production, implement proper password hashing)
