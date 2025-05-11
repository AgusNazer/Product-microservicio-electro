# Etapa de compilación
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app_products.jar
# Crear un archivo .env vacío


ENTRYPOINT ["java", "-jar", "app_products.jar"]
EXPOSE 8769
