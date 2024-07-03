FROM openjdk:17

# Crear y cambiar al directorio /app
WORKDIR /app

COPY . /app

# Le damos los permisos necesarios para poder ejecutar gradlew
RUN chmod +x ./gradlew

RUN ./gradlew build
RUN ./gradlew test