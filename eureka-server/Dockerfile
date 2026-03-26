# Use lightweight Java image
FROM eclipse-temurin:17-jdk-alpine

# Working directory set
WORKDIR /app

# Copy jar file
COPY target/eureka-server-0.0.1-SNAPSHOT.jar app.jar

# Expose port (Eureka default)
EXPOSE 8761

# Run application
ENTRYPOINT ["java", "-jar", "app.jar"]