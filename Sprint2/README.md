# Sistema de verificacion del clima (Sprint2)

### 1. Contenerización del Sistema:

**Dockerfile**

```Dockerfile
FROM openjdk:17

WORKDIR /app

COPY . /app

RUN chmod +x ./gradlew build

CMD ["java", "-cp", "build/classes/java/main", "org.example.Main"]

```

Como podemos ver la imagen se creo exitosamente:

![img_12.png](Imagenes%2Fimg_12.png)

Es la primera imagen crada hace 1 minuto aproximadamente:

![img_13.png](Imagenes%2Fimg_13.png)

Como podemos ver el contenedor ejecuta nuestra aplicacion con exito:

![img_14.png](Imagenes%2Fimg_14.png)

Ahora corramos el contenedor


### 2. Refinamiento del TDD:

Asegurar que todas las pruebas existentes pasen en el entorno Dockerizado.

```Dockerfile
FROM openjdk:17

# Crear y cambiar al directorio /app
WORKDIR /app

COPY . /app

# Le damos los permisos necesarios para poder ejecutar gradlew
RUN chmod +x ./gradlew

RUN ./gradlew build
RUN ./gradlew test
```

Como podemos ver la imagen se creo exitosamente :

![img_17.png](Imagenes%2Fimg_17.png)

Podemos ver que es la ultima imagen creada hace aproximadamente 1 minuto...

![img_18.png](Imagenes%2Fimg_18.png)

Por ultimo, corremos el contenedor, vemos que no hay ningun error por lo que las pruebas pasaron exitosamente:

![img_19.png](Imagenes%2Fimg_19.png)

