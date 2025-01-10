# Task Manager Web Application

**Technologies:** Java, Spring Security (with JWT), Spring JPA, Spring MVC, PostgreSQL, JUnit, Mockito  

## Overview  
This project is a comprehensive **task management web application** designed to help users efficiently manage tasks with a clear distinction between user and admin roles. The application is built using **modern backend technologies** and focuses on secure authentication, intuitive features, and maintainability.  

## Installation  

To set up and run the Task Manager Web Application locally, follow these steps:  

### Prerequisites  
Make sure you have the following installed on your system:  
- **Java JDK 17+**  
- **Maven 3.8+**  
- **PostgreSQL 14+**  
- **Git**  

### Steps  

1. **Clone the Repository**  
   ```bash
   git clone https://github.com/your-username/task-manager.git
   cd task-manager
   
2. **Set Up the Database**  

   - Create a PostgreSQL database (e.g., `task_manager_db`).
   - You can install environment variables via IDE or .env file:
    ```properties
     ${DATABASE_URL} for url
     ${DATABASE_USERNAME} for username
     ${DATABASE_PASSWORD} for password
     ```
   - Or update the database configuration in the `application.properties` file:  
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/task_manager_db
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     spring.jpa.hibernate.ddl-auto=update
     ```
  

3. **Build the Project**  

   Use Maven to build the project:  
   ```bash
   mvn clean install

4. **Run the application**  

   Start the application using:  
   ```bash
   mvn spring-boot:run

5. **Access the Application**
   - **Open your browser and navigate to http://localhost:8080.**
   - **You can log in or register a new user.**

## Key Functionalities  

### User Role Features  
- **Task Management:**  
  - Create new tasks with relevant details.  
  - View tasks where they are either the **author** or **executor**.  
  - Delete tasks they have authored.  
- A user-friendly interface ensures seamless interaction with tasks.  

### Admin Role Features  
- **Admin Dashboard:**  
  - Access a centralized admin page with an overview of all system entities.  
- **User Management:**  
  - View all registered users in the system.  
  - Edit user roles (e.g., promote to admin or restrict access).  
  - Delete users if necessary.  
- **Task Management:**  
  - View all tasks in the system, regardless of ownership.  
  - Edit and update any task details to maintain data consistency.  

## Architecture  

### Secure Authentication & Authorization  
- Implements **JWT** (JSON Web Tokens) for stateless and secure user authentication.  
- Role-based access control ensures restricted actions for different user types (e.g., users vs. admins).  

### Spring MVC Design Pattern  
- The application follows a clean separation of concerns, using controllers, services, and repositories for modularity and maintainability.  

### Data Persistence  
- The application relies on **Spring JPA** for seamless integration with the **PostgreSQL** database, ensuring efficient data handling and query execution.  

### Testing and Code Reliability  
- Unit and integration tests are implemented using **JUnit** and **Mockito** to ensure high code quality, reliability, and robust error handling.  

## Technical Highlights  
- **Task Delegation:** Users can delegate tasks to others and monitor task progress.  
- **Dynamic Role Management:** Admins can dynamically change user roles and permissions directly from the admin dashboard.  
- **Validation and Error Handling:** Comprehensive validation ensures data integrity, while meaningful error messages improve the user experience.  
- **Responsive Design:** While the backend focuses on functionality, the frontend (not detailed here) ensures a responsive design for various devices.  

## Learning Outcomes and Challenges  
Through this project, I honed my skills in:  
- Building secure, scalable, and maintainable applications with **Spring Framework**.  
- Implementing stateless authentication using **JWT**.  
- Writing reliable test cases for different layers of the application.  
- Working with relational databases (**PostgreSQL**) and designing efficient queries using JPA.  
- Managing role-based access control in multi-user applications.  

This project is a testament to my ability to develop practical, real-world solutions that balance security, usability, and scalability.  
