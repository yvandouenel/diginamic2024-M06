# Étape de build
FROM maven:3.8.4-openjdk-11-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Étape de runtime
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/movie-db-1.0-SNAPSHOT-jar-with-dependencies.jar ./app.jar

# Attente de la base de données et démarrage de l'application
CMD ["java", "-jar", "app.jar"]