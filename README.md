# SA58_XiaTian_Fibonacci

This project includes a Spring Boot backend and a React frontend. The Spring Boot application provides a REST API to optimize coin denominations, and the React application serves as the frontend interface.

## Prerequisites

- Docker
- Docker Compose

## Building and Running the Containers

1. Clone the repository:

    ```bash
    git clone https://github.com/329946325/SA58_XiaTian_Fibonacci.git
    cd SA58_YourName_Fibonacci
    ```

2. Build and run the Docker containers:

    ```bash
    docker-compose up --build
    ```

    This command will build the Docker images and start the containers for both the Spring Boot backend and React frontend.

3. Access the applications:
    - React frontend: `http://localhost:3000`

3. Test the applications:
    - Example 1:
        Input:
            Target amount: 7.3
            Coin denominations (comma-separated): 0.1,0.5,1,5,10
        Output: 
            0.1, 0.1, 0.1, 1, 1, 5

    - Example 2:
        Input:
            Target amount: 103
            Coin denominations (comma-separated): 1,2,50
        Output: 
            1, 2, 50, 50



## Java Spring Boot
- Spring Boot backend: `http://localhost:8080/api/coins/optimize`

## Dockerfile for Spring Boot Application

```dockerfile
# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/your-application-name.jar app.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
