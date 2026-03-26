# Java base image
FROM eclipse-temurin:17-jdk-alpine

# Working directory
WORKDIR /app

# Copy jar file
COPY target/notification-service-0.0.1-SNAPSHOT.jar app.jar

# Expose port (notification service)
EXPOSE 8086

# Run application
ENTRYPOINT ["java", "-jar", "app.jar"]