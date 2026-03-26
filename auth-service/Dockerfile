# Java base image
FROM eclipse-temurin:17-jdk-alpine

# Working directory
WORKDIR /app

# Copy jar file
COPY target/auth-service-0.0.1-SNAPSHOT.jar app.jar

# Expose port (auth service port)
EXPOSE 8081

# Run application
ENTRYPOINT ["java", "-jar", "app.jar"]