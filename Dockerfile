FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Kopiera pom.xml och ladda ner dependencies först (snabbare builds)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Kopierar resten av projektet
COPY src ./src

# Bygger jar-filen
RUN mvn clean package -DskipTests

# Kör fas -liten och snabb JRE-bild
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Kopierar jar från build-steget
COPY --from=build /app/target/*.jar app.jar

# Exponera porten Spring Boot kör på
EXPOSE 8080

# Startar applikationen
ENTRYPOINT ["java", "-jar", "app.jar"]