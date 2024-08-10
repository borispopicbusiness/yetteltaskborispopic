Hi,

This project was developed as part of the task assigned by Yettel Bank Serbia
for the job application.

Below are the instructions for testing, compiling, and running the project.
The project uses Maven, so here are the relevant Maven commands:

Before you begin:

Java 17, the SDK
Maven
Docker

I tried to send the zip file over Gmail, however mvnw.cmd had been detected by Google so id did not allow me to send it.
So, I needed to rename it, now It is mvnw.cmdRenameMe. Please rename it, it has to be mvnw.cmd. You may regenerate
the maven wrapper, but just rename it. If you have any issue after, then my suggestion is to check my GitHub repository.

https://github.com/borispopicbusiness/yetteltaskborispopic


Testing:

    mvn test

Compiling

    mvn clean install

Running:

    mvn exec:java -Dexec.mainClass="org.borispopic.yetteltask.YetteltaskApplication"


REST API Endpoints

Here’s how you can interact with the available API endpoints:

--------------------------------------------------------------------------------------------------------

1. Get User by ID

To fetch a user by ID:

    curl -X GET -H "Content-Type: application/json" "http://localhost:8080/api/v1/user/get/{id}"

For example, to get the user with ID 1:

    curl -X GET -H "Content-Type: application/json" "http://localhost:8080/api/v1/user/get/1"

--------------------------------------------------------------------------------------------------------

2. Create a New User

To create a new user, send a POST request with the necessary data:

curl -X POST -H "Content-Type: application/json" "http://localhost:8080/api/v1/user/create" -d '{ \
    "first_name": "Boris",
    "last_name": "Popic",
    "email": "borispopic.business@gmail.com",
    "address": "some address 000",
    "phone": "555-1234",
    "birth_date": "1985-01-15"
}'

The response will be the DTO of the created user.

---------------------------------------------------------------------------------------------------------

3. Delete a User

To delete a user by ID:

    curl -X DELETE -H "Content-Type: application/json" "http://localhost:8080/api/v1/user/delete/{id}"

For example, to delete the user with ID 4:

    curl -X DELETE -H "Content-Type: application/json" "http://localhost:8080/api/v1/user/delete/4"

The response will be the DTO of the deleted user.

---------------------------------------------------------------------------------------------------------

4. Update User Information

To update user information, send a PUT request with the updated data:

curl -X PUT -H "Content-Type: application/json" "http://localhost:8080/api/v1/user/update" -d '{ \
    "id": "4",
    "first_name": "Boris",
    "last_name": "Popic",
    "email": "borispopic.business@gmail.com",
    "address": "some updated address 000",
    "phone": "555-1234"
}'

The response will be the updated user DTO.

---------------------------------------------------------------------------------------------------------

5. Get Account by ID

To fetch an account by ID:

curl -X GET -H "Content-Type: application/json" "http://localhost:8080/api/v1/account/get/{id}"

For example, to get the account with ID 2:

curl -X GET -H "Content-Type: application/json" "http://localhost:8080/api/v1/account/get/2"

The response will be the corresponding account DTO.

---------------------------------------------------------------------------------------------------------

6. Create a New Account

To create a new account:

curl -X POST -H "Content-Type: application/json" "http://localhost:8080/api/v1/account/create" -d '{ \
    "owner_id": "2",
    "balance": "350.00",
    "account_type": "CHECKING"
}'

Feel free to reach out if you have any questions or need further assistance!
