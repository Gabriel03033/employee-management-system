# Employee Management System Project

### About the project:

The **Employee Management System** project represents the starting implementation of a dashboard where representatives of an IT company can manage their employees and mentors they are assigned to.

### Technology stack:

* **Programming languages & technologies**: Java, Spring Core, Spring Boot, JUnit
* **IDEs (Integrated Development Environment)**: IntelliJ
* **Databases/Data access providers**: MySQL
* **Build automation tools**: Maven
* **Versioning systems**: Git
* **Web servers**: Tomcat

### Spring modules used:

This project is a simple Spring Boot web application that came into existence after learning and delving into several Spring Boot basic functionalities including:

* **Spring Application Context** - beans management inside the IoC (inversion of control) container
* **Spring Boot Web App** - usage of the MVC (Model-View-Controller) architectural design and Thymeleaf as a template engine
* **Spring Boot REST Services** - provide fronted-backend separation by providing JSON formatted responses to the user's HTTP requests
* **Spring Data Access** - provide data storage using Spring Data JPA (Java Persistence API) and Hibernate framework as an ORM (Object Relational Mapping) implementation
* **Spring Testing** - provide unit tests for increased project flow maintainability

### How to use Java Backend Locally:

For every software installed, add the binary folder to the system environment variables, then open the command line and check if the installation was successful by checking the version.

1. Install and configure JDK (Java Development
   Kit) [https://www.oracle.com/java/technologies/javase/jdk18-archive-downloads.html](https://www.oracle.com/java/technologies/javase/jdk18-archive-downloads.html).

```bash
java --version
```

2. Install and configure Maven [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi).

```bash
mvn --version
```

4. Install and configure MySQL Server and Workbench [https://dev.mysql.com/downloads/mysql/](https://dev.mysql.com/downloads/mysql/).

```bash
mysql --version
```

5. To clone the project in your local repository, copy the '.git' link inside the code section of the GitHub repository and choose HTTPS as the cloning method. In the command line type:

```bash
git clone https://github.com/Gabriel03033/employee-management-system.git
```

6. Install an IDE (Integrated Development Environment) that supports Java to run the application (IntelliJ is
   preferred): [https://www.jetbrains.com/idea/download/?fromIDE=#section=windows](https://www.jetbrains.com/idea/download/?fromIDE=#section=windows)
7. In MySQL Workbench create a new query. Enter and run the following command:

```sql
  CREATE DATABASE employee_management_system;
```

8. Inside the src/main/resources/application.properties file modify the username and password with the credentials set in the installation wizard:

```properties
spring.datasource.username=root
spring.datasource.password=[insert_your_password]
```

9. Execute the application by running the main() method inside src/main/java/com/project/it/EmployeeManagementSystemApplication.java or by executing de terminal command: `./mvnw clean spring-boot:run`
   . This should activate the Tomcat Container and provide access to http://localhost:8080 and create the tables needed inside the database.

10. For inserting arbitrary data in some tables inside the src/main/resources/application.properties uncomment the following properties and run the application again:

```properties
spring.sql.init.mode=always
spring.sql.init.schema-locations=classpath:init-data.sql
```

Make sure you comment back these properties for any subsequent application executions.
