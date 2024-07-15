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
        - Input:
            - Target amount: 7.3
            - Coin denominations (comma-separated): 0.1,0.5,1,5,10
        - Output: 
           - 0.1, 0.1, 0.1, 1, 1, 5

    - Example 2:
        - Input:
            - Target amount: 103
            - Coin denominations (comma-separated): 1,2,50
        - Output: 
            - 1, 2, 50, 50
              
==============================================================================
Due to the time limit for the video, I include some introductions here for your kind reference :)
## Challenge 1: Logic and Problem Solving (Done)
    ## Java Spring Boot
        1. Coin Master Controller (Prioritize larger value coins)
            Firstly I complete a preliminary version called "Coin Master Controller" with simple logic to select coin from the largest denomination.
            But this logic main induce some problems, because prioritize to use large amount coin first may lead to more small coin, which is not people usually want:
                e.g. $0.4 = $0.35 * 1 + $0.01 * 5 (total 6 coins)
                          = $0.1 * 4 (total 4 coins)
        2. Coin OptimizerController (Mininum number of total coin amount)
            So I design the logic to maintain the mininum number of the total amount of coins. Every pair of current amount and last coin used is stored into a hashmap. For the given expected amount, it will look for the paired last coin and deduct from the total amount and so on （A bit of recursive thinking).
            In addition, this controller is converted into a rest api controller to easier future implementation.
            Spring Boot backend: `http://localhost:8080/api/coins/optimize`
## Bonus
    ## React JS (Done)
        React JS is implemented for the front end UI desin.
        React frontend: `http://localhost:3000`

## Challenge 2: Containers (Done)
    ## Docker
        Both the front end(Coin Master Folder) and back end(coin-optimize-spp) design have a DockerFile inside, and these two form a composed `docker-compose.yml` to build the environment. The structure is as below:
            SA58_YourName_Fibonacci/
            ├── README.md
            ├── docker-compose.yml
            ├── CoinMaster
            │   ├── Dockerfile
            │   ├── src/
            │   └── ...
            └── coin-optimizer-app
                ├── Dockerfile
                ├── src/
                └── ...

## Challenge 3: Cloud Deployment 
    # EC2 public DNS: ec2-47-128-244-66.ap-southeast-1.compute.amazonaws.com
    Due to some issues during registering Oracle Cloud, I choose to use AWS for cloud deployment.
    My Steps:
        - Create Usergroup and User (Done)
        - Create ECR repository (Done)
        - Lauch and connect to EC2 instance (Done)
        - Install docker and docker compose (Done)
        - Deploy the applciation (Done)
        - Verify the deployment 
               I encounter some problems when trying to connect to the public DNS of my EC2 instance.
                   Actions: Check Container Status
                            Check security gourp rules
                            Inspect Logs
                            Ensure Containers are Networked Correctly
                    But still haven't find out the root cause and still working on it...

Thank you for your time and have a nice day!





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
