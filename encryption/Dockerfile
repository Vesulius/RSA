FROM openjdk:16-alpine3.13

WORKDIR /usr/src/app

COPY . .

RUN ./gradlew build

RUN chmod +x ./build/libs/encryption.jar

CMD ["java", "-jar", "./build/libs/encryption.jar"]