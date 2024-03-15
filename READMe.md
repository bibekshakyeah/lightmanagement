# Light Management System

## Overview
This project is a light management system designed to control and manage lights in a building. It provides functionalities to control the brightness, color, and schedule of lights.

## Setup and Running Instructions

### Prerequisites
- Java Development Kit (JDK) installed
- Apache Maven installed (for building from source)
- Docker installed (optional, for running with Docker)

### Running with IntelliJ IDEA
1. Open the project in IntelliJ IDEA.
2. Navigate to the main class.
3. Run the main class.

### Running with Maven
1. Open a terminal.
2. Navigate to the project directory.
3. Run the following commands:

```mvn clean install
java -jar target/lightmanagement-0.0.1-SNAPSHOT.jar

```


### Running with Docker
1. Open a terminal.
2. Navigate to the project directory.
3. Build the Docker image using the following command:

```
docker build -t lightmanagement .

docker run -it --rm lightmanagement

```

## Design Choices
- **Java:** Java was chosen as the primary programming language due to its platform independence and robust ecosystem.
- **Maven:** Maven is used as the build tool for managing dependencies and building the project.
- **Docker:** Docker is supported to containerize the application, making it easier to deploy and manage across different environments.

