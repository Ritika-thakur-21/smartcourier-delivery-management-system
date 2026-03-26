# Java base image
FROM eclipse-temurin:17-jdk-alpine

# Working directory
WORKDIR /app

# Copy jar file
COPY target/api-gateway-0.0.1-SNAPSHOT.jar app.jar

# Expose port (Gateway default)
EXPOSE 8080

# Run app
ENTRYPOINT ["java", "-jar", "app.jar"]