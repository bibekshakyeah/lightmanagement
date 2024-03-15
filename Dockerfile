# Stage 1: Build Maven dependencies
FROM maven:3.8.4-openjdk-17-slim AS build-dependencies

# Set the working directory in the container
WORKDIR /app

# Copy only the Maven project definition
COPY pom.xml .

# Resolve Maven dependencies (cache the dependencies)
RUN mvn dependency:go-offline

# Stage 2: Build the project
FROM build-dependencies AS build

# Copy the entire project source
COPY src ./src

# Build the project
RUN mvn package

# Stage 3: Final image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the compiled Java application JAR file from the build stage
COPY --from=build /app/target/lightmanagement-0.0.1-SNAPSHOT.jar /app/my-application.jar

# Command to run the Java application
CMD ["java", "-jar", "my-application.jar"]
