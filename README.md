# translation-management
Translation Management

Prerequisites
- Java 17
- PostgreSql

Prepare Database
- create database in postgres
- Update database credentials in application.properties

Run the project
- Clone the project
- Run maven command: mvn clean package

Swagger
http://localhost:9000/swagger-ui/index.html

Design Choice
The project follows a layered architecture with a clear separation of concerns, making it maintainable, scalable, and modular. Encourages SOLID principles and clean architecture.

Design Overview
Controller Layer
- handles incoming request

Service Layer
- Contains business logic

Persistence Layer
- Entity: defines entities, representing database tables.
- Repository: database layer, to handle database calls.

Mapper Layer
- Convert entities to responseDTO
- Convert requestDTO to entities
- Keeps the service layer clean
