# P6_OC_Full_Stack

This project is the sixth of the OpenClassrooms full stack developer formation. Its goal is to learn how to create a blog from scratch using Java with Spring for the back, and JavaScript with Angular for the front.

## Prerequisites

- MySQL >= 8
- Angular >= 14.1.0
- Java >= 8
- Maven

## Create the DB

1. Copy the SQL script located in ./back/src/main/resources/sql/script.sql
2. Import it inside your MySQL shell
3. In the ./back/src/main/resources/application.properties file, replace the followings by your informations:
- spring.datasource.url=jdbc:mysql://localhost:3306/test?allowPublicKeyRetrieval=true
- spring.datasource.username=user
- spring.datasource.password=123456

## Start the project

Clone the repository:
- `git clone https://github.com/Hugo-LML/P6_OC_Full_Stack.git`

### Back

Open a terminal in the back folder and run:
- `mvn clean install`
- `mvn spring-boot:run`

### Front

Open a terminal in the front folder and run:
- `npm install`
- `npm run start`

### Application

Ckick on "S'inscrire" to create a new account and get access to the blog.
