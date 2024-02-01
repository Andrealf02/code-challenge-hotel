# Reservation System

This project is a reservation management application that allows users to make and manage accommodation reservations. The application includes features such as reservation statistics calculation and profit maximization.

## Table of Contents

1. [Project Structure](#project-structure)
2. [Technologies Used](#technologies-used)
3. [Methodologies](#methodologies)
4. [How to Run the Application](#how-to-run-the-application)
5. [How to Deploy with Docker](#how-to-deploy-with-docker)
6. [How to Run Tests](#how-to-run-tests)
7. [API Documentation](#api-documentation)
8. [Contributions](#contributions)
9. [License](#license)

## Project Structure

The project follows a hexagonal architecture (Ports and Adapters) and is designed under the principles of Domain-Driven Design (DDD) with main folders for different components:

- `src/main/java/org/main/booking`: Contains the application source code.
    - `application`: Includes application logic and services.
    - `domain`: Defines the domain model and entities.
    - `adapter`: Contains application adapters, such as controllers and repositories.
    - `config`: Application configurations.
- `src/test`: Contains unit tests.
- `src/main/resources`: Contains resources, such as configuration files.

## Technologies Used

- Java
- Spring Boot
- Lombok


## Methodologies

The development of this project follows the following methodologies:

1. **Agile Methodology (Scrum):** Agile practices have been employed for iterative and incremental development, allowing adaptation to changes and continuous delivery of value to the client.

2. **Test-Driven Development (TDD):** TDD practices have been followed to ensure adequate test coverage and improve code quality.

3. **The hexagonal architecture:** also known as Ports and Adapters, organizes a system into independent layers, allowing the separation of business logic from external code, thereby facilitating adaptability and testing.

4. **Domain-Driven Design (DDD):** is a software design methodology that focuses on understanding and modeling the problem domain to develop more effective solutions aligned with business needs.

## How to Run the Application

1. Clone the repository: `git clone https://github.com/your-username/code-challenge-hotel`
2. Navigate to the project directory: `cd stayForLong`
3. Run the application: `./mvnw spring-boot:run`

The application will be available at [http://localhost:8080](http://localhost:8080).

## How to Run Tests

To run tests, use the following command:

```bash
./mvnw test
