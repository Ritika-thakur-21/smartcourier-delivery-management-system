# Java base image
FROM eclipse-temurin:17-jdk-alpine

# Working directory
WORKDIR /app

# Copy jar file
COPY target/config-server-0.0.1-SNAPSHOT.jar app.jar

# Expose port (Config Server default)
EXPOSE 8888

# Run app
ENTRYPOINT ["java", "-jar", "app.jar"]