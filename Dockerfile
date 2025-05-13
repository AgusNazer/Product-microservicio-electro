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

# Variables de entorno para la conexión a la base de datos en producción
ENV SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/tienda_electro
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=alma2022
ENV SPRING_DATASOURCE_DRIVER_CLASS_NAME=com.mysql.cj.jdbc.Driver
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update
ENV SPRING_JPA_SHOW_SQL=true
ENV SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT=org.hibernate.dialect.MySQL8Dialect

# Puerto de la aplicación Spring Boot
EXPOSE 8769

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app_products.jar"]