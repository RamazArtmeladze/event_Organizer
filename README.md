Event Organizer is a project built with Spring that helps users plan and manage their event expenses. It provides a centralized platform for expense management and budget planing.

Prerequisites

Before running the project, ensure you have the following software installed on your machine

1. Java Development Kit (JDK) 8 or later
2. PostgreSQL database (version 9.6 or later)
3. IDE (IntelliJ IDEA ) or any text editor

Getting Started

1. Clone the repository.
2. Set up the database.
-- Install PostgreSQL database on your machine if you haven't already.
Create a new database for the project. You can use the following command in the PostgreSQL terminal or a tool like pgAdmin:
"CREATE DATABASE event_organizer"

-- Update the database configuration in the project:
Open the src/main/resources/application.properties file. Modify the following lines to match your PostgreSQL configuration:
spring.datasource.url=jdbc:postgresql://localhost:5432/eventOrganizer.
spring.datasource.username=your_username.
spring.datasource.password=your_password.

Replace your_username and your_password with your PostgreSQL credentials.

3. Database Migrations:
The project uses database migrations managed by Flyway. you can instal and get more information about it from the following link: https://flywaydb.org/documentation/getstarted/why 

4. Running the Application:

-- Open the project in your preferred IDE (IntelliJ IDEA is recommended).
-- Build the project to resolve any dependencies.
-- Run the main class src/main/java/com.app.eventOrganizer/EventOrganizerApplication.java to start the application.
-- Once the application is running, you can access it by visiting http://localhost:8080 in your web browser or using a tool like Postman.
-- You can try the available endpoints, such as /health, to verify that the application is running and connected to the database.

Congratulations! You have successfully set up and run the Event Organizer project locally.
