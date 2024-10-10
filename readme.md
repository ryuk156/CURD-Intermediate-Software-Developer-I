# User Management API - CURD


## Overview




#### This is a REST API for managing users, built using Java 17 and the Spring Framework. The service provides endpoints to create, list, update, and delete users. It stores the following user information:

- Username
- First Name
- Last Name
- Email
- Phone Number

##### The API uses a file-based H2 database in file mode to ensure that user data is persistently stored. This configuration allows the service to retain all user information across restarts, as the database is saved to a specific file on the disk. This setup not only maintains data integrity but also facilitates easy backups and migrations when needed, providing a reliable and convenient way to manage data.

#### Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database (file-based for persistent data)
- Maven (for build and dependency management)

### Setup & Installation

To run the project locally, follow these steps:

1. Clone the repository:
```
git clone https://github.com/ryuk156/CURD-Intermediate-Software-Developer-I.git
```
2. Navigate to the project directory:
```
cd CURD-Intermediate-Software-Developer-I
```
3. Build the Project
```
mvn spring-boot:run
```
4. Run the application
```
mvn spring-boot:run
```
The application will start on the default port 8080.


### API Endpoints

#### 1. Create a new Use
- Endpoint: POST /users
- Request Body:
 
```
{
  "username": "john_doe",
  "firstname": "John",
  "lastname": "Doe",
  "email": "john.doe@example.com",
  "phonenumber": "123-456-7890"
}

```

#### 2.  List all users
- Endpoint: GET /users
- Response:
    - 200 OK with a list of all users in the database.

#### 3. Get a single user by ID
- Endpoint: GET /users/{id}
- Response:
    - 200 OK with the details of the user.
    - 404 Not Found if the user with the given ID does not exist.

#### 4. Update an existing user
- Endpoint: PUT /users/{id}
- Request Body:
```
{
  "username": "updated_username",
  "firstname": "UpdatedFirstName",
  "lastname": "UpdatedLastName",
  "email": "updated.email@example.com",
  "phonenumber": "987-654-3210"
}
```

- Response: 
    - 200 OK with the updated user details.
    - 404 Not Found if the user with the given ID does not exist

#### 5. Delete a user
- Endpoint: DELETE /users/{id}
- Response:
    - 204 No Content if the deletion was successful.
    - 404 Not Found if the user with the given ID does not exist.

Testing the API
You can use Postman, Thunderclient or cURL to test the API.
