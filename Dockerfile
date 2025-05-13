# Etapa de construcción
FROM maven:3.8-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/products-0.0.1.jar app_products.jar
ENTRYPOINT ["java", "-jar", "app_products.jar"]

