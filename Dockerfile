# Build stage
FROM maven:3.8.5-openjdk-17-slim AS build
WORKDIR /app

# Copy the project files from the subdirectory to the build context
COPY QuantityMeasurementApplication/pom.xml .
COPY QuantityMeasurementApplication/src ./src

# Build the application
RUN mvn clean package -DskipTests

# Run stage
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Set the port environment variable (Render provides this, defaulting to 8080)
ENV PORT=8080
EXPOSE 8080

# Command to run the application
# Using sh -c to ensure the PORT environment variable is correctly expanded at runtime
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT}"]
