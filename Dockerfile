# Java base image
FROM eclipse-temurin:17-jdk-alpine

# Working directory
WORKDIR /app

# Copy jar file
COPY target/admin-service-0.0.1-SNAPSHOT.jar app.jar

# Expose port (admin service)
EXPOSE 8084

# Run application
ENTRYPOINT ["java", "-jar", "app.jar"]