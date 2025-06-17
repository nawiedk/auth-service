FROM eclipse-temurin:21-jdk
LABEL authors="Nawied"
WORKDIR /app
COPY target/auth-service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]