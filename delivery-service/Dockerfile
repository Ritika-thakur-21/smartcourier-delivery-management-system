# Java base image
FROM eclipse-temurin:17-jdk-alpine

# Working directory
WORKDIR /app

# Copy jar file
COPY target/delivery-service-0.0.1-SNAPSHOT.jar app.jar

# Expose port (delivery service)
EXPOSE 8082

# Run application
ENTRYPOINT ["java", "-jar", "app.jar"]