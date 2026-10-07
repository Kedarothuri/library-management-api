# Library Management System

## Project overview

This project is a library management application built with Java and Spring Boot. It provides a REST API and simple browser pages for library staff and students. The application keeps book, member, student, and borrowing information in a database.

The central workflow is:

1. A student submits their name, email address, and student card ID.
2. The new student starts with the `PENDING` registration status.
3. An administrator approves or rejects the registration.
4. An approved student can borrow a book that is currently available.
5. When a book is returned, it becomes available again and the borrowing record is marked as returned.

There is also a general member registration API. In the current borrowing flow, however, borrowing requires an approved `Student` account.

## Main features

- Add books and view the book list.
- Register general members and students.
- View all students or filter them by registration status.
- Approve or reject student registrations.
- Borrow an available book for an approved student.
- Return a borrowed book.
- Store a borrow log with the borrow time and, after return, the return time.
- Serve simple home, admin, and student pages from the application.
- Return structured error responses for missing resources and unavailable borrowing actions.

## Technology

- **Java 17** — programming language and required Java version.
- **Spring Boot 4.1.1** — application framework used to start and configure the application.
- **Spring Web MVC** — maps HTTP requests to API controller methods.
- **Spring Data JPA / Hibernate** — maps Java model objects to database records and performs database operations.
- **Jakarta Bean Validation** — validation support included in the project dependencies.
- **Microsoft SQL Server** — configured as the normal application database.
- **H2** — in-memory database configured for tests.
- **Maven** — dependency and build management; the repository includes Maven wrapper scripts.
- **Lombok** — included as a project dependency and configured as an annotation processor.

## Project structure

All Java code is under `src/main/java/com/example/librarymanagementapi`.

| Package | Purpose |
| --- | --- |
| `controller` | Defines HTTP routes and receives requests. |
| `service` and `service/impl` | Holds the application operations and their implementations. |
| `repository` | Provides database access for the model classes. |
| `model` | Defines the persisted concepts, such as `Book`, `Member`, `Student`, and `BorrowLog`. |
| `dto/request` | Defines data accepted from API callers. |
| `dto/response` | Defines data sent back to API callers. |
| `mapper` | Converts between a book request/entity and a book response. |
| `exception` | Defines application errors and translates them into HTTP error responses. |

The browser pages are in `src/main/resources/static`: `index.html`, `admin.html`, and `Student.html`.

## API reference

The server listens on port `8080` by default. All routes below are relative to `http://localhost:8080`.

| Method | Path | Purpose |
| --- | --- | --- |
| `GET` | `/api/books` | Get all books. |
| `POST` | `/api/books` | Add a book. JSON body: `{"title":"Clean Code","author":"Robert C. Martin"}`. |
| `PUT` | `/api/books/{bookId}/borrow/{memberId}` | Borrow a book. The ID must identify an approved student. |
| `PUT` | `/api/books/{bookId}/return` | Return a borrowed book. |
| `GET` | `/api/members` | Get all general members. |
| `POST` | `/api/members` | Register a general member. JSON body: `{"name":"Alex Example","email":"alex@example.com"}`. |
| `POST` | `/api/students/register?name=Alex%20Example&email=alex@example.com&studentCardId=STU-1001` | Register a student. The values are request parameters. |
| `GET` | `/api/students` | Get all students. |
| `GET` | `/api/students?status=PENDING` | Get students with the specified status (`PENDING`, `APPROVED`, or `REJECTED`). |
| `PUT` | `/api/students/{id}/approve` | Approve a student. |
| `PUT` | `/api/students/{id}/reject` | Reject a student. |

Successful API responses use JSON. A missing book or student is reported as HTTP `404`; an unavailable book or an unapproved student attempting to borrow is reported as HTTP `400`. Other unexpected errors are handled as HTTP `500`.

## Run locally

### Requirements

- Java 17 or later.
- Microsoft SQL Server running locally, with a database named `LibraryDB`.
- Database credentials available in the environment variables `DB_USERNAME` and `DB_PASSWORD`.

The database connection URL and port are configured in `src/main/resources/application.properties`. Hibernate is configured to update the schema automatically (`spring.jpa.hibernate.ddl-auto=update`).

### Start the application on Windows

In PowerShell, set the database credentials for the current terminal, then start the application:

```powershell
$env:DB_USERNAME = "your_sql_server_username"
$env:DB_PASSWORD = "your_sql_server_password"
.\mvnw.cmd spring-boot:run
```

Open `http://localhost:8080/` for the home page. The admin page is at `http://localhost:8080/admin.html` and the student page is at `http://localhost:8080/Student.html`.

The repository includes an H2 in-memory database configuration under test resources. The standard application configuration uses SQL Server.

## Key terms used in this project

| Term | Meaning in this project |
| --- | --- |
| **API (Application Programming Interface)** | The set of HTTP routes clients use to work with books, members, and students. |
| **REST API** | An API that uses HTTP methods such as `GET`, `POST`, and `PUT` to read or change resources. |
| **Endpoint / route** | A specific method and URL combination, such as `GET /api/books`. |
| **Controller** | A class that receives web requests, calls application logic, and returns HTTP responses. Examples: `BookController` and `StudentController`. |
| **Service** | The layer that carries out application rules, such as checking approval before a student borrows a book. |
| **Repository** | The database access layer used to find and save model objects. |
| **Entity** | A Java class mapped to a database table. `Book`, `Member`, `Student`, and `BorrowLog` are entities. |
| **JPA (Java Persistence API)** | The Java standard used to map objects to relational database data. |
| **Hibernate** | The JPA implementation used by this application to communicate with the database. |
| **DTO (Data Transfer Object)** | An object shaped for data entering or leaving the API. Request DTOs accept data; response DTOs format returned data. |
| **Mapper** | Code that converts one representation into another, such as `BookRequest` into a `Book` entity or a `Book` into `BookResponse`. |
| **Dependency injection** | Spring's mechanism for supplying a class with objects it needs, such as a controller receiving its service. |
| **HTTP method** | The action type in a web request: `GET` reads, `POST` creates/submits, and `PUT` updates in these routes. |
| **Path variable** | A value embedded in the URL, such as `{bookId}` in `/api/books/{bookId}/return`. |
| **Request parameter** | A named value supplied after `?` in a URL or as form data, such as `status=PENDING`. |
| **JSON** | A text format used for structured request and response data. |
| **Status code** | The number in an HTTP response that describes the result, such as `200` for success, `400` for a rejected operation, and `404` for a missing resource. |
| **Enum** | A type with a fixed set of allowed values. `RegistrationStatus` allows `PENDING`, `APPROVED`, and `REJECTED`. |
| **Inheritance** | A class reuses fields and behavior from another class. Here, `Student` extends `Member`, so a student has member details plus a student card ID and registration status. |
| **Relationship (many-to-one)** | A database association where multiple books can refer to the same member as their borrower over time; the current book record stores its current borrower. |
| **Borrow log** | A record connecting a student and book with borrow and return timestamps. |
| **Transaction** | A group of database changes treated as one operation. Borrowing and returning are marked transactional so their related changes are handled together. |
| **Exception handler** | Code that catches application errors and turns them into consistent HTTP responses. `GlobalExceptionHandler` performs this role. |
| **CORS (Cross-Origin Resource Sharing)** | A browser security mechanism governing requests from a different web origin. The API controllers currently allow requests from any origin. |

## Simple explanation for a mentor

> “I built a library management application using Java and Spring Boot. It has REST endpoints and basic web pages for managing books, members, and students. A student registers with a student card ID and must be approved by an administrator before borrowing. The book service checks that approval and that the book is available, then updates the book and creates a borrow log. When the book is returned, it becomes available again and the log gets a return time. The code is organized into controllers, services, repositories, entities, DTOs, and exception handling, with SQL Server as the configured application database.”
