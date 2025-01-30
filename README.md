# Monitor Sensors

## Project Description

Monitor Sensors — is a CRUD application based on Spring Boot that allows you to manage sensors. The system uses two predefined user roles:
- **admin** (role Administrator): has access to all entities and can perform all actions (addition, deletion, editing, viewing).
- **user** (role Viewer): has access only to viewing the list of sensors and searching by data fields ("name" and "model").

### Technology Stack
- Java 17
- Spring Boot
- Spring Security
- Hibernate
- PostgreSQL
- Lombok
- Flyway for database migrations
- Swagger for API documentation

### Installation and Launch

#### Requirements
- Docker
- Maven
- Java 17

### Setup Steps

1. **Clone the repository**

   ```sh
   git clone https://github.com/yourusername/monitor-sensors.git

2. **Build the project**
   
   ```sh
   mvn clean package

3. **Run Docker containers**
   ```sh
   docker-compose up --build

### Using Swagger

Swagger UI provides an interface for testing your application's API. To log in to Swagger UI, you need to use user credentials

#### User Accounts

- **admin**: `admin/admin`
- **user**: `user/user`

#### How to Use Swagger

1. Go to [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui.html).
2. Click the "Authorize" button in the top right corner.
3. ВEnter the user credentials:
   - for administrator: `admin/admin`
   - for viewer: `user/user`
4. After successful authorization, you will be able to use various API endpoints to interact with the application.



