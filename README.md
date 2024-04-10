# h2-implementation
Project to Integrate h2 in memory database to spring boot application

Requirements:
Java 21
Maven

To test the application using swagger, start the application and 
go to - http://localhost:8080/swagger-ui/index.html 

The swagger displays 4 endpoints:
1. GET - To retrieve all the employees.
2. POST - To add a new Employee.
3. PUT - To update an existing Employee.
4. DELETE - To remove an existing Employee

To check your h2 database, start the application and 
go to - http://localhost:8080/h2-console
Use the credentials from the application.properties file to login to the console.
Select on the table and run the query to view the pre-loaded data.
You can add/update/remove the data from here if needed.