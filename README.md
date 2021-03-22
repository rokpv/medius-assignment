# medius-assignment

Medius student assignment for Rok Petrovčič Vižintin.

The assignment is a simple Lights Out implementation where a user can create and solve problems, and also get solutions for chosen problems.

The project uses Spring Boot, H2 database and JPA on the backend and Angular on the frontend.

## Frontend
To run the frontend app, you will need Node.js and NPM. Navigate into the `frontend` folder.
From there, run `npm install` to install the dependencies and `npm start` to launch the app.
The app can be accessed at `http://localhost:4200/`.

## Backend
To run and compile the backend, you will need Java 8 and Maven. Navigate into the `backend` folder.
Run `mvn compile && mvn package` to compile the application. Then, run it with `java -jar target/assignment-rpv-0.0.1-SNAPSHOT.jar`.
The server runs on `http://localhost:8080/`. The API is documented with Swagger2 and can be accessed at `http://localhost:8080/v2/api-docs`. 
