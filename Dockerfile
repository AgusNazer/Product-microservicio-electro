# Etapa de compilación
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests
RUN ls -l /app/target  # Verifica si el archivo .jar está allí

# Etapa de ejecución
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app_products.jar
ENTRYPOINT ["java", "-jar", "app_products.jar"]
EXPOSE 8769
