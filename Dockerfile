# Etapa de compilación
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .

# Aquí se construye el proyecto
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/products-0.0.1.jar app_products.jar

# Copiar el archivo .env al contenedor
COPY .env /app/.env

ENTRYPOINT ["java", "-jar", "app_products.jar"]
EXPOSE 8769
