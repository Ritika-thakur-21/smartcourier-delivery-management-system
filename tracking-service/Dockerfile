# Java base image
FROM eclipse-temurin:17-jdk-alpine

# Working directory
WORKDIR /app

# Copy jar file
COPY target/tracking-service-0.0.1-SNAPSHOT.jar app.jar

# Expose port (tracking service)
EXPOSE 8083

# Run application
ENTRYPOINT ["java", "-jar", "app.jar"]