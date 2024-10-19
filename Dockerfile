# Usa una imagen base de Java
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR de tu aplicación al contenedor
COPY target/GestionDeVuelos-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto que utiliza tu aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]